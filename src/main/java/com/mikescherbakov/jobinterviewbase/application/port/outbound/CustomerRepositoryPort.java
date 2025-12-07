package com.mikescherbakov.jobinterviewbase.application.port.outbound;

import com.mikescherbakov.jobinterviewbase.infrastructure.persistence.entity.CustomerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerRepositoryPort {

  CustomerEntity save(CustomerEntity entity);

  Page<CustomerEntity> findAll(Pageable pageable);

  long count();

  void deleteByName(String name);
}
