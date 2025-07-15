package com.mikescherbakov.jobinterviewbase.runner;

import com.mikescherbakov.jobinterviewbase.service.DemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AspectDemo implements ApplicationRunner {

  private final DemoService demoService;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    System.out.println(demoService.getDemoMessage());
  }
}
