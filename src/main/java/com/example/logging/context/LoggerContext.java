package com.example.logging.context;
import org.slf4j.MDC;
import java.util.UUID;
public final class LoggerContext {
    public static final String CORRELATION_ID_KEY="correlationId";
    public static final String FEATURE_CORRELATION_ID_KEY="featureCorrelationId";
    public static String getCorrelationId(){return MDC.get(CORRELATION_ID_KEY);}
    public static String setCorrelationId(String id){
        if(id==null||id.isBlank()) id=UUID.randomUUID().toString();
        MDC.put(CORRELATION_ID_KEY,id); return id;
    }
    public static String getOrCreateCorrelationId(){
        String id=MDC.get(CORRELATION_ID_KEY);
        if(id==null) id=setCorrelationId(null);
        return id;
    }
    public static void setFeatureCorrelationId(String f){
        if(f!=null&&!f.isBlank()) MDC.put(FEATURE_CORRELATION_ID_KEY,f);
    }
    public static String getFeatureCorrelationId(){return MDC.get(FEATURE_CORRELATION_ID_KEY);}
    public static void clear(){MDC.clear();}
}
