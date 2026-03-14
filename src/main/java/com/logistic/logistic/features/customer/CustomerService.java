package com.logistic.logistic.features.customer;

import com.logistic.logistic.features.customer.dto.CustomerResponse;

import java.util.List;

public interface CustomerService {
    List<CustomerResponse> findAll();
    CustomerResponse findById(Integer id);
}
