package com.mikescherbakov.jobinterviewbase.infrastructure.persistence.repository;

import com.mikescherbakov.jobinterviewbase.application.port.outbound.CustomerRepositoryPort;
import com.mikescherbakov.jobinterviewbase.infrastructure.persistence.entity.CustomerEntity;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Profile("postgres-jpa")
@Repository
public interface CustomerEntityRepository
    extends JpaRepository<CustomerEntity, String>, CustomerRepositoryPort {
  @Transactional
  void deleteByName(String name);
}
