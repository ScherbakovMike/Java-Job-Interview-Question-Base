package com.mikescherbakov.jobinterviewbase.application.port.outbound;

import com.mikescherbakov.jobinterviewbase.domain.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GetCustomerUseCase {

  Page<Customer> getCustomers(Pageable request);
}
