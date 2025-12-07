package com.mikescherbakov.jobinterviewbase.application.service;

import com.mikescherbakov.jobinterviewbase.application.dto.CreateCustomerRequest;
import com.mikescherbakov.jobinterviewbase.application.dto.CreateCustomerResponse;
import com.mikescherbakov.jobinterviewbase.application.port.inbound.CreateCustomerUseCase;
import com.mikescherbakov.jobinterviewbase.application.port.outbound.GetCustomerUseCase;
import com.mikescherbakov.jobinterviewbase.domain.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService extends CreateCustomerUseCase, GetCustomerUseCase {

  CreateCustomerResponse createCustomer(CreateCustomerRequest request);

  Page<Customer> getCustomers(Pageable request);
}
