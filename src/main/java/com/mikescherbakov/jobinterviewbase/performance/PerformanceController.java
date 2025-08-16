package com.mikescherbakov.jobinterviewbase.performance;

import io.micrometer.core.instrument.Timer;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PerformanceController {

  private final Timer timer;

  // Access results by link:
  // http://localhost:8080/actuator/metrics/my.timer
  @GetMapping("/time")
  public String timeSomething(Authentication authentication) {
    Runnable action = () -> {};

    timer.record(action);
    return "Action has been recorded. Visitor: %s with roles: %s".formatted(authentication.getName(), authentication.getAuthorities());
  }
}
