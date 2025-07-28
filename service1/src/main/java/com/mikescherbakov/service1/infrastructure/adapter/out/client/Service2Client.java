package com.mikescherbakov.service1.infrastructure.adapter.out.client;

import org.springframework.cloud.openfeign.*;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "service2", url = "${services.service2.url}")
public interface Service2Client {

    @GetMapping("/")
    String getPage();
}
