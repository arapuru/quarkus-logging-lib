package com.example.logging.sink;
import com.example.logging.config.LoggingConfig;
import com.example.logging.model.LogEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import java.util.*;
@ApplicationScoped
public class LogSinkRegistry {
 private final List<LogSink> sinks=new ArrayList<>();
 @Inject
 public LogSinkRegistry(LoggingConfig cfg, Instance<ConsoleLogSink> c, Instance<FileLogSink> f, Instance<DatadogLogSink> d){
   for(String s: cfg.getSinkNames()){
     s=s.trim().toLowerCase();
     if(s.equals("console")) sinks.add(c.get());
     if(s.equals("file")) sinks.add(f.get());
     if(s.equals("datadog")) sinks.add(d.get());
   }
 }
 public void publish(LogEvent e){ sinks.forEach(x->x.publish(e)); }
}
