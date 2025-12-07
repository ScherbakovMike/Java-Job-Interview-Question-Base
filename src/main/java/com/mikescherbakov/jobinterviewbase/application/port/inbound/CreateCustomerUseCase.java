package com.mikescherbakov.jobinterviewbase.application.port.inbound;

import com.mikescherbakov.jobinterviewbase.application.dto.CreateCustomerRequest;
import com.mikescherbakov.jobinterviewbase.application.dto.CreateCustomerResponse;

public interface CreateCustomerUseCase {

  CreateCustomerResponse createCustomer(CreateCustomerRequest request);
}
