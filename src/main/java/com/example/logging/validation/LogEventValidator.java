package com.example.logging.validation;
import com.example.logging.model.LogEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.*;
import java.util.Set;
@ApplicationScoped
public class LogEventValidator {
 private final Validator validator;
 public LogEventValidator(){
   ValidatorFactory f=Validation.buildDefaultValidatorFactory();
   validator=f.getValidator();
 }
 public void validate(LogEvent e){
   Set<ConstraintViolation<LogEvent>> v=validator.validate(e);
   if(!v.isEmpty()) throw new IllegalArgumentException("Invalid log event");
 }
}
