package com.mikescherbakov.jobinterviewbase.infrastructure.mapping;

import com.mikescherbakov.jobinterviewbase.application.dto.CreateCustomerRequest;
import com.mikescherbakov.jobinterviewbase.application.dto.CreateCustomerResponse;
import com.mikescherbakov.jobinterviewbase.domain.model.Customer;
import com.mikescherbakov.jobinterviewbase.infrastructure.persistence.entity.CustomerDocument;
import java.util.List;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@Profile({"mongo-jpa", "mongo-manual"})
public class CustomerDocumentMapper {

  public CustomerDocument toCustomerDocument(CreateCustomerRequest request) {
    return new CustomerDocument(request.id(), request.name(), request.registrationDateTime());
  }

  public CreateCustomerResponse toCreateCustomerResponse(CustomerDocument customerDocument) {
    return new CreateCustomerResponse(toCustomer(customerDocument), List.of());
  }

  private Customer toCustomer(CustomerDocument customerDocument) {
    return new Customer(
        customerDocument.getId(),
        customerDocument.getName(),
        customerDocument.getRegistrationDateTime());
  }

  public Page<Customer> toPageCustomer(Page<CustomerDocument> pageDocuments) {
    return pageDocuments.map(this::toCustomer);
  }
}
