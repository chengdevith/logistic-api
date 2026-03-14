package com.logistic.logistic.features.customer;

import com.logistic.logistic.domain.Customer;
import com.logistic.logistic.features.customer.dto.CustomerResponse;
import com.logistic.logistic.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<CustomerResponse> findAll() {

        List<Customer> customers = customerRepository.findAll();

        return customerMapper.toCustomersResponse(customers);
    }

    @Override
    public CustomerResponse findById(Integer id) {
        return customerMapper.toCustomerResponse(customerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Customer " + id + " Not found"
                )));
    }
}
