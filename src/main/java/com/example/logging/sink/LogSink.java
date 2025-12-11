package com.example.logging.sink;
import com.example.logging.model.LogEvent;
public interface LogSink { void publish(LogEvent e); }
