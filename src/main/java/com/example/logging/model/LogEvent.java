package com.example.logging.model;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import java.time.Instant;
import java.util.Map;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LogEvent {
    @NotNull private Instant timestamp;
    @NotBlank private String level;
    @NotBlank private String message;
    @NotBlank private String serviceName;
    @NotBlank private String correlationId;
    private String featureCorrelationId;
    private String phase;
    private String loggerName;
    private String threadName;
    private Map<String,Object> details;
    public Instant getTimestamp(){return timestamp;}
    public void setTimestamp(Instant t){this.timestamp=t;}
    public String getLevel(){return level;}
    public void setLevel(String l){this.level=l;}
    public String getMessage(){return message;}
    public void setMessage(String m){this.message=m;}
    public String getServiceName(){return serviceName;}
    public void setServiceName(String s){this.serviceName=s;}
    public String getCorrelationId(){return correlationId;}
    public void setCorrelationId(String c){this.correlationId=c;}
    public String getFeatureCorrelationId(){return featureCorrelationId;}
    public void setFeatureCorrelationId(String f){this.featureCorrelationId=f;}
    public String getPhase(){return phase;}
    public void setPhase(String p){this.phase=p;}
    public String getLoggerName(){return loggerName;}
    public void setLoggerName(String l){this.loggerName=l;}
    public String getThreadName(){return threadName;}
    public void setThreadName(String t){this.threadName=t;}
    public Map<String,Object> getDetails(){return details;}
    public void setDetails(Map<String,Object> d){this.details=d;}
}
