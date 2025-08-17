package com.mikescherbakov.jobinterviewbase.performance;

import io.micrometer.core.instrument.Timer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PerformanceController {

  private final Timer timer;

  public PerformanceController(Timer timer) {
    this.timer = timer;
  }

  // Access results by link:
  // http://localhost:8080/actuator/metrics/my.timer
  @GetMapping("/time")
  public String timeSomething() {
    Runnable action = () -> {};

    timer.record(action);
    return "Action has been recorded.";
  }

  @GetMapping("/discover")
  public ResponseEntity<String> discover() {
    return ResponseEntity.ok("External key-based authentication has been completed.");
  }
}
