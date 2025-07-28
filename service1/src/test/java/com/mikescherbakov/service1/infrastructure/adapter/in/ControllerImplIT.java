package com.mikescherbakov.service1.infrastructure.adapter.in;

import com.github.tomakehurst.wiremock.client.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.cloud.contract.wiremock.*;
import org.springframework.test.context.*;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureWireMock(port = 8089)
@ActiveProfiles("test")
@TestPropertySource(properties = "services.service2.url=http://localhost:8089")
class ControllerImplIT {

    @Autowired
    private ControllerImpl controller;

    private static final String MOCK_RESPONSE_BODY = "Google page";

    @BeforeEach
    void setup() {
        WireMock.reset();
    }

    @Test
    void shouldReturnMockedData() {
        stubFor(get(urlEqualTo("/"))
                .willReturn(
                        aResponse()
                                .withBody(MOCK_RESPONSE_BODY)
                ));
        var response = controller.getPage();
        assertEquals(MOCK_RESPONSE_BODY, response);
    }
}