package com.mikescherbakov.jobinterviewbase.infrastructure.mapping;

import com.mikescherbakov.jobinterviewbase.application.dto.CreateCustomerRequest;
import com.mikescherbakov.jobinterviewbase.application.dto.CreateCustomerResponse;
import com.mikescherbakov.jobinterviewbase.domain.model.Customer;
import com.mikescherbakov.jobinterviewbase.infrastructure.persistence.entity.CustomerEntity;
import java.util.List;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@Profile({"postgres-jpa", "postgres-manual"})
public class CustomerJpaEntityMapper {

  public CustomerEntity toCustomerEntity(CreateCustomerRequest request) {
    return new CustomerEntity(null, request.name(), request.registrationDateTime());
  }

  public CreateCustomerResponse toCreateCustomerResponse(CustomerEntity customerEntity) {
    return new CreateCustomerResponse(toCustomer(customerEntity), List.of());
  }

  private Customer toCustomer(CustomerEntity customerEntity) {
    return new Customer(
        customerEntity.getId(), customerEntity.getName(), customerEntity.getRegistrationDateTime());
  }

  public Page<Customer> toPageCustomer(Page<CustomerEntity> pageEntities) {
    return pageEntities.map(this::toCustomer);
  }
}
