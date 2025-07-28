package com.mikescherbakov.service1.application.service;

import com.mikescherbakov.service1.domain.port.in.*;
import com.mikescherbakov.service1.domain.port.out.*;
import lombok.*;
import org.springframework.stereotype.*;

@Service
@RequiredArgsConstructor
public class ServiceImpl implements UseCase {

    private final ExternalServiceUseCase controller;

    @Override
    public String getPage() {
        return controller.getPage();
    }
}
