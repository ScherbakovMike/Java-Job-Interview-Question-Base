package com.mikescherbakov.springstarterautoconfigure.service;

import com.mikescherbakov.springstarterautoconfigure.properties.*;
import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.boot.context.properties.*;
import org.springframework.context.annotation.*;

@Configuration
@EnableConfigurationProperties(GreeterProperties.class)
@ConditionalOnProperty(
    prefix = "greeter.service",
    name = "enabled",
    havingValue = "true")
public class GreeterAutoConfiguration {
  private final GreeterProperties properties;

  public GreeterAutoConfiguration(GreeterProperties properties) {
    this.properties = properties;
  }

  @Bean
  @ConditionalOnMissingBean
  public GreeterService greeterService() {
    return new GreeterService(
        properties.getSalutation(), properties.getUserName(), properties.getSuffix());
  }
}
