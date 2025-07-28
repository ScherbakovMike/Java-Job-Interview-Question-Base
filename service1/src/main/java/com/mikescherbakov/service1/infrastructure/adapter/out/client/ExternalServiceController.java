package com.mikescherbakov.service1.infrastructure.adapter.out.client;

import com.mikescherbakov.service1.domain.port.out.*;
import lombok.*;
import org.springframework.stereotype.*;

@Service
@RequiredArgsConstructor
public class ExternalServiceController implements ExternalServiceUseCase {

    private final Service2Client client;

    @Override
    public String getPage() {
        return client.getPage();
    }
}
