package com.example.logging.config;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import java.util.List;
@ApplicationScoped
public class LoggingConfig {
 @ConfigProperty(name="logging.service-name",defaultValue="unknown-service")
 String serviceName;
 @ConfigProperty(name="logging.sinks",defaultValue="console")
 String sinks;
 @ConfigProperty(name="logging.file.path",defaultValue="logs/app.log")
 String filePath;
 @ConfigProperty(name="logging.datadog.url",defaultValue="http://localhost:8126/v1/input")
 String datadogUrl;
 public String getServiceName(){return serviceName;}
 public List<String> getSinkNames(){return List.of(sinks.split(","));}
 public String getFilePath(){return filePath;}
 public String getDatadogUrl(){return datadogUrl;}
}
