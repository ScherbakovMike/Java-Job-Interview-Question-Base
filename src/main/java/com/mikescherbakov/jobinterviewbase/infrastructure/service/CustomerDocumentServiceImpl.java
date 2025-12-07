package com.mikescherbakov.jobinterviewbase.infrastructure.service;

import com.mikescherbakov.jobinterviewbase.application.dto.CreateCustomerRequest;
import com.mikescherbakov.jobinterviewbase.application.dto.CreateCustomerResponse;
import com.mikescherbakov.jobinterviewbase.application.port.outbound.CustomerDocumentRepositoryPort;
import com.mikescherbakov.jobinterviewbase.application.service.CustomerService;
import com.mikescherbakov.jobinterviewbase.domain.model.Customer;
import com.mikescherbakov.jobinterviewbase.infrastructure.mapping.CustomerDocumentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Profile({"mongo-jpa", "mongo-manual"})
@Service
@RequiredArgsConstructor
public class CustomerDocumentServiceImpl implements CustomerService {

  private final CustomerDocumentRepositoryPort repository;
  private final CustomerDocumentMapper customerDocumentMapper;

  @Override
  public CreateCustomerResponse createCustomer(CreateCustomerRequest request) {
    return customerDocumentMapper.toCreateCustomerResponse(
        repository.save(customerDocumentMapper.toCustomerDocument(request)));
  }

  @Override
  public Page<Customer> getCustomers(Pageable request) {
    return customerDocumentMapper.toPageCustomer(repository.findAll(request));
  }
}
