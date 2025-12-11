package com.example.logging.interceptor;
import com.example.logging.config.LoggingConfig;
import com.example.logging.context.LoggerContext;
import com.example.logging.model.LogEvent;
import com.example.logging.otel.OTelTracing;
import com.example.logging.sink.LogSinkRegistry;
import com.example.logging.validation.LogEventValidator;
import io.opentelemetry.api.trace.Span;
import jakarta.annotation.Priority;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.interceptor.*;
import java.time.Instant;
import java.util.*;
@Start @InProgress @BeforeRequest @AfterRequest @End
@Interceptor @Priority(Interceptor.Priority.APPLICATION)
@Dependent
public class LoggingInterceptor {
 @Inject LogSinkRegistry registry;
 @Inject LoggingConfig cfg;
 @Inject LogEventValidator validator;
 @Inject OTelTracing otel;
 @AroundInvoke
 public Object around(InvocationContext ctx) throws Exception {
   long start=System.currentTimeMillis();
   String correlation=LoggerContext.getOrCreateCorrelationId();
   String phase="IN_PROGRESS";
   Span span=otel.startSpan(ctx.getMethod().getName());
   span.setAttribute("correlation.id",correlation);
   try{
     Object result=ctx.proceed();
     long dur=System.currentTimeMillis()-start;
     publish(ctx,"EXECUTION_SUCCESS","INFO",phase,dur,null);
     span.setAttribute("duration.ms",dur);
     span.end();
     return result;
   }catch(Exception ex){
     long dur=System.currentTimeMillis()-start;
     publish(ctx,ex.getMessage(),"ERROR",phase,dur,ex);
     span.recordException(ex);
     span.end();
     throw ex;
   }
 }
 private void publish(InvocationContext ctx,String msg,String level,String phase,long dur,Exception ex){
   LogEvent ev=new LogEvent();
   ev.setTimestamp(Instant.now());
   ev.setLevel(level);
   ev.setMessage(msg);
   ev.setServiceName(cfg.getServiceName());
   ev.setCorrelationId(LoggerContext.getOrCreateCorrelationId());
   ev.setFeatureCorrelationId(LoggerContext.getFeatureCorrelationId());
   ev.setPhase(phase);
   ev.setLoggerName(ctx.getMethod().getDeclaringClass().getName());
   ev.setThreadName(Thread.currentThread().getName());
   Map<String,Object> d=new HashMap<>();
   d.put("method",ctx.getMethod().getName());
   d.put("durationMs",dur);
   if(ex!=null)d.put("exception",ex.getClass().getName());
   ev.setDetails(d);
   validator.validate(ev);
   registry.publish(ev);
 }
}
