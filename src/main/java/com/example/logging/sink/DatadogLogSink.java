package com.example.logging.sink;
import com.example.logging.config.LoggingConfig;
import com.example.logging.model.LogEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped; import jakarta.inject.Inject;
import java.net.*; import java.net.http.*;
@ApplicationScoped
public class DatadogLogSink implements LogSink {
 @Inject LoggingConfig cfg;
 private final ObjectMapper mapper=new ObjectMapper();
 private final HttpClient client=HttpClient.newHttpClient();
 public void publish(LogEvent e){
   try{
     HttpRequest r=HttpRequest.newBuilder(URI.create(cfg.getDatadogUrl()))
       .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(e)))
       .header("Content-Type","application/json").build();
     client.sendAsync(r,HttpResponse.BodyHandlers.discarding());
   }catch(Exception ex){ex.printStackTrace();}
 }
}
