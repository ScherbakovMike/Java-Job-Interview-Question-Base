package com.mikescherbakov.service1.infrastructure.adapter.in;

import com.mikescherbakov.service1.domain.port.in.*;
import lombok.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ControllerImpl {

    private final UseCase service;

    @GetMapping
    public String getPage() {
        return service.getPage();
    }
}
