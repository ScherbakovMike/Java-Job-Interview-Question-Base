package com.mikescherbakov.jobinterviewbase.application.port.outbound;

import com.mikescherbakov.jobinterviewbase.infrastructure.persistence.entity.CustomerDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerDocumentRepositoryPort {

  CustomerDocument save(CustomerDocument document);

  Page<CustomerDocument> findAll(Pageable pageable);

  long count();

  void deleteByName(String name);
}
