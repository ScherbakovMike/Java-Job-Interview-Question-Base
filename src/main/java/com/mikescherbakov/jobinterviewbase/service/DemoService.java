package com.mikescherbakov.jobinterviewbase.service;

import java.util.concurrent.TimeUnit;
import org.springframework.stereotype.Service;

@Service
public class DemoService {

  public String getDemoMessage() throws InterruptedException {
    TimeUnit.SECONDS.sleep(1);
    return "Hello World!";
  }
}
