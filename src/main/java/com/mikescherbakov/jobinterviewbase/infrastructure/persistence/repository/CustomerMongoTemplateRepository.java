package com.mikescherbakov.jobinterviewbase.infrastructure.persistence.repository;

import com.mikescherbakov.jobinterviewbase.application.port.outbound.CustomerDocumentRepositoryPort;
import com.mikescherbakov.jobinterviewbase.infrastructure.persistence.entity.CustomerDocument;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Profile("mongo-manual")
@Repository
@RequiredArgsConstructor
public class CustomerMongoTemplateRepository implements CustomerDocumentRepositoryPort {

  private final MongoTemplate mongoTemplate;

  public CustomerDocument save(CustomerDocument document) {
    return mongoTemplate.save(document);
  }

  public Page<CustomerDocument> findAll(Pageable pageable) {
    long total = count();

    Query query = new Query().with(pageable);
    List<CustomerDocument> content = mongoTemplate.find(query, CustomerDocument.class);

    return new PageImpl<>(content, pageable, total);
  }

  public long count() {
    return mongoTemplate.count(new Query(), CustomerDocument.class);
  }

  public void deleteByName(String name) {
    Query query = new Query(Criteria.where("name").is(name));
    mongoTemplate.remove(query, CustomerDocument.class);
  }
}
