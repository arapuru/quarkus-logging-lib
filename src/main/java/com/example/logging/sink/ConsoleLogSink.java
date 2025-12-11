package com.example.logging.sink;
import com.example.logging.model.LogEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import org.slf4j.Logger; import org.slf4j.LoggerFactory;
@ApplicationScoped
public class ConsoleLogSink implements LogSink {
 private static final Logger log=LoggerFactory.getLogger(ConsoleLogSink.class);
 private final ObjectMapper mapper=new ObjectMapper();
 public void publish(LogEvent e){
   try{ log.info(mapper.writeValueAsString(e)); }catch(Exception ex){ ex.printStackTrace(); }
 }
}
