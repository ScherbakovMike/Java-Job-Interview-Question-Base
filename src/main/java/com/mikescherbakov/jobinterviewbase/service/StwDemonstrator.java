package com.mikescherbakov.jobinterviewbase.service;

import jakarta.annotation.*;
import java.time.*;
import java.time.format.*;
import java.util.concurrent.*;
import org.springframework.stereotype.*;

@Component
public class StwDemonstrator {

  @PostConstruct
  public void start() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    var executor = Executors.newSingleThreadScheduledExecutor();
    executor.scheduleAtFixedRate(
        () -> {
          // This message will be interrupted during long STW pauses
          System.out.println("Application is alive at: " + LocalTime.now().format(formatter));
        },
        0,
        1,
        TimeUnit.SECONDS);
  }
}
