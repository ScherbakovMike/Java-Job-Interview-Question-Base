package com.mikescherbakov.service2.application.service;

import com.mikescherbakov.service2.domain.port.in.*;
import com.mikescherbakov.service2.domain.port.out.*;
import lombok.*;
import org.springframework.stereotype.*;

@Service
@RequiredArgsConstructor
public class ServiceImpl implements UseCase {

    private final ExternalServiceUseCase controller;

    @Override
    public String getPage() {
        String service3Response = controller.getPage();
        return "Service2 processed: " + service3Response;
    }
}