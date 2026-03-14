package com.logistic.logistic.mapper;

import com.logistic.logistic.domain.Customer;
import com.logistic.logistic.features.auth.dto.RegisterRequest;
import com.logistic.logistic.features.customer.dto.CustomerResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    List<CustomerResponse> toCustomersResponse(List<Customer> customers);
    CustomerResponse toCustomerResponse (Customer customers);
    Customer fromRegisterRequest(RegisterRequest registerRequest);
}
