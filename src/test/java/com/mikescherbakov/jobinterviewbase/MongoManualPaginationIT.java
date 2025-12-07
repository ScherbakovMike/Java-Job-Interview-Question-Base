package com.mikescherbakov.jobinterviewbase;

import com.mikescherbakov.jobinterviewbase.application.dto.CreateCustomerRequest;
import com.mikescherbakov.jobinterviewbase.application.port.outbound.CustomerDocumentRepositoryPort;
import java.time.Instant;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@ActiveProfiles(profiles = {"mongo-manual"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class MongoManualPaginationIT {

  @Autowired private WebTestClient webClient;
  @Autowired private CustomerDocumentRepositoryPort customerDocumentRepository;

  @BeforeEach
  void cleanup() {
    customerDocumentRepository.deleteByName("test name");
  }

  @Test
  void createCustomerSuccessful() {
    CreateCustomerRequest request =
        new CreateCustomerRequest(UUID.randomUUID(), "test name", Instant.now());

    webClient
        .post()
        .uri("/customers")
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(request)
        .exchange()
        .expectStatus()
        .isCreated()
        .expectBody()
        .jsonPath("$.customer.name")
        .isEqualTo(request.name())
        .jsonPath("$.customer.id")
        .isNotEmpty()
        .jsonPath("$.customer.registrationDateTime")
        .isEqualTo(request.registrationDateTime().toString());
  }

  @Test
  void checkPaginationByDefault() {
    webClient
        .get()
        .uri("/customers")
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody()
        .jsonPath("$.content")
        .isArray()
        .jsonPath("$.content.length()")
        .isEqualTo(20)
        .jsonPath("$.pageable.pageNumber")
        .isEqualTo(0)
        .jsonPath("$.pageable.pageSize")
        .isEqualTo(20)
        .jsonPath("$.totalElements")
        .isEqualTo(1000)
        .jsonPath("$.totalPages")
        .isEqualTo(50)
        .jsonPath("$.first")
        .isEqualTo(true)
        .jsonPath("$.last")
        .isEqualTo(false)
        .jsonPath("$.number")
        .isEqualTo(0)
        .jsonPath("$.size")
        .isEqualTo(20);
  }

  @Test
  void checkPaginationByPage() {
    webClient
        .get()
        .uri("/customers?page=5&size=100")
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody()
        .jsonPath("$.content")
        .isArray()
        .jsonPath("$.content.length()")
        .isEqualTo(100)
        .jsonPath("$.pageable.pageNumber")
        .isEqualTo(5)
        .jsonPath("$.pageable.pageSize")
        .isEqualTo(100)
        .jsonPath("$.totalElements")
        .isEqualTo(1000)
        .jsonPath("$.totalPages")
        .isEqualTo(10)
        .jsonPath("$.first")
        .isEqualTo(false)
        .jsonPath("$.last")
        .isEqualTo(false)
        .jsonPath("$.number")
        .isEqualTo(5)
        .jsonPath("$.size")
        .isEqualTo(100);
  }
}
