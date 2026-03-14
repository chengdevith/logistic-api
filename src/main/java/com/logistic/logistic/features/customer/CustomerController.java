package com.logistic.logistic.features.customer;

import com.logistic.logistic.features.customer.dto.CustomerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    List<CustomerResponse> getAllCustomers(){
        return customerService.findAll();
    }

    @GetMapping("/{id}")
    CustomerResponse getCustomerById(@PathVariable Integer id){
        return customerService.findById(id);
    }

}
