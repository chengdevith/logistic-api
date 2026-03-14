package com.logistic.logistic.security;

import com.logistic.logistic.domain.Customer;
import com.logistic.logistic.features.customer.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username: " + username);
        Customer customer = customerRepository.findCustomersByEmail(username).orElseThrow(
                () -> new UsernameNotFoundException(username)
        );
        CustomUserDetails customUserDetails = new CustomUserDetails();
        customUserDetails.setCustomer(customer);
        return customUserDetails;
    }
}
