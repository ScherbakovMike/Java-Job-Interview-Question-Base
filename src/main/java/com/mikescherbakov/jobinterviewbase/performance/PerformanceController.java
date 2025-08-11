package com.mikescherbakov.jobinterviewbase.performance;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PerformanceController {

  private final MeterRegistry meterRegistry;

  public PerformanceController(MeterRegistry meterRegistry) {
    this.meterRegistry = meterRegistry;
  }

  // Access results by link:
  // http://localhost:8080/actuator/metrics/my.timer
  @GetMapping("/time")
  public String timeSomething() {
    Runnable action = () -> {};

    Timer timer = Timer.builder("my.timer").register(meterRegistry);
    timer.record(action);
    return "Action has been recorded.";
  }
}
