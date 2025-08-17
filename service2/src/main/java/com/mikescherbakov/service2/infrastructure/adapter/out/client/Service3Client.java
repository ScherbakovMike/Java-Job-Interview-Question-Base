package com.mikescherbakov.service2.infrastructure.adapter.out.client;

import org.springframework.cloud.openfeign.*;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "service3", url = "${services.service3.url}")
public interface Service3Client {

    @GetMapping("/")
    String getPage();
}