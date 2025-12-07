package com.mikescherbakov.jobinterviewbase.infrastructure.persistence.repository;

import com.mikescherbakov.jobinterviewbase.application.port.outbound.CustomerDocumentRepositoryPort;
import com.mikescherbakov.jobinterviewbase.infrastructure.persistence.entity.CustomerDocument;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Profile("mongo-jpa")
@Repository
public interface CustomerDocumentRepository
    extends MongoRepository<CustomerDocument, String>, CustomerDocumentRepositoryPort {
  void deleteByName(String name);
}
