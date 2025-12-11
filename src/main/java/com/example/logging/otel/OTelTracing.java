package com.example.logging.otel;
import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.trace.*;
import jakarta.enterprise.context.ApplicationScoped;
@ApplicationScoped
public class OTelTracing {
 private final Tracer tracer=GlobalOpenTelemetry.getTracer("quarkus-logging-lib");
 public Span startSpan(String name){ return tracer.spanBuilder(name).setSpanKind(SpanKind.INTERNAL).startSpan(); }
}
