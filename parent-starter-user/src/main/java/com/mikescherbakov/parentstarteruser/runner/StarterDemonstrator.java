package com.mikescherbakov.parentstarteruser.runner;

import com.mikescherbakov.springstarterautoconfigure.service.*;
import lombok.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.stereotype.*;

@Component
@ConditionalOnProperty(prefix = "greeter.service", name = "enabled", havingValue = "true")
@RequiredArgsConstructor
public class StarterDemonstrator implements ApplicationRunner {

  private final GreeterService greeterService;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    System.out.println(greeterService.greet());
  }
}
