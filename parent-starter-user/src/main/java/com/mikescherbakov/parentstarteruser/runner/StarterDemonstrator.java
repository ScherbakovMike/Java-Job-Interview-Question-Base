package com.mikescherbakov.parentstarteruser.runner;

import com.mikescherbakov.springstarterautoconfigure.service.*;
import lombok.*;
import org.springframework.boot.*;
import org.springframework.stereotype.*;

@Component
@RequiredArgsConstructor
public class StarterDemonstrator implements ApplicationRunner {

  private final GreeterService greeterService;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    System.out.println(greeterService.greet());
  }
}
