package com.mikescherbakov.jobinterviewbase.infrastructure.service;

import com.mikescherbakov.jobinterviewbase.application.dto.CreateCustomerRequest;
import com.mikescherbakov.jobinterviewbase.application.dto.CreateCustomerResponse;
import com.mikescherbakov.jobinterviewbase.application.port.outbound.CustomerRepositoryPort;
import com.mikescherbakov.jobinterviewbase.application.service.CustomerService;
import com.mikescherbakov.jobinterviewbase.domain.model.Customer;
import com.mikescherbakov.jobinterviewbase.infrastructure.mapping.CustomerJpaEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Profile({"postgres-jpa", "postgres-manual"})
@Service
@RequiredArgsConstructor
public class CustomerEntityServiceImpl implements CustomerService {

  private final CustomerRepositoryPort repository;
  private final CustomerJpaEntityMapper customerJpaEntityMapper;

  @Override
  public CreateCustomerResponse createCustomer(CreateCustomerRequest request) {
    return customerJpaEntityMapper.toCreateCustomerResponse(
        repository.save(customerJpaEntityMapper.toCustomerEntity(request)));
  }

  @Override
  public Page<Customer> getCustomers(Pageable request) {
    return customerJpaEntityMapper.toPageCustomer(repository.findAll(request));
  }
}
