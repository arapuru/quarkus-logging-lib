package com.example.logging.sink;
import com.example.logging.config.LoggingConfig;
import com.example.logging.model.LogEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.nio.file.*; import java.io.IOException;
@ApplicationScoped
public class FileLogSink implements LogSink {
 @Inject LoggingConfig cfg;
 private final ObjectMapper mapper=new ObjectMapper();
 public void publish(LogEvent e){
   try{
     Path p=Path.of(cfg.getFilePath());
     Files.createDirectories(p.getParent());
     Files.writeString(p,mapper.writeValueAsString(e)+System.lineSeparator(),
         StandardOpenOption.CREATE,StandardOpenOption.APPEND);
   }catch(IOException ex){ex.printStackTrace();}
 }
}
