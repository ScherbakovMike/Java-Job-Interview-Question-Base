package com.mikescherbakov.service2.infrastructure.adapter.out.client;

import com.mikescherbakov.service2.domain.port.out.*;
import lombok.*;
import org.springframework.stereotype.*;

@Service
@RequiredArgsConstructor
public class ExternalServiceController implements ExternalServiceUseCase {

    private final Service3Client client;

    @Override
    public String getPage() {
        return client.getPage();
    }
}