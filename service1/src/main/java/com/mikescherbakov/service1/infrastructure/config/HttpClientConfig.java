package com.mikescherbakov.service1.infrastructure.config;


import org.springframework.cloud.openfeign.*;
import org.springframework.context.annotation.*;

@Configuration
@EnableFeignClients(basePackages = "com.mikescherbakov.service1.infrastructure.adapter.out.client")
public class HttpClientConfig {

}