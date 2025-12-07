package com.mikescherbakov.jobinterviewbase.infrastructure.configuration;

import com.mikescherbakov.jobinterviewbase.infrastructure.persistence.entity.CustomerDocument;
import jakarta.annotation.PostConstruct;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
@Profile({"mongo-jpa", "mongo-manual"})
@RequiredArgsConstructor
public class MongoDataInitializer {

  private final MongoTemplate mongoTemplate;

  @PostConstruct
  public void init() {
    if (mongoTemplate.count(
            new org.springframework.data.mongodb.core.query.Query(), CustomerDocument.class)
        == 0) {
      List<CustomerDocument> customers =
          IntStream.rangeClosed(1, 1000)
              .mapToObj(
                  i -> new CustomerDocument(UUID.randomUUID(), "Customer " + i, Instant.now()))
              .toList();
      mongoTemplate.insertAll(customers);
    }
  }
}
