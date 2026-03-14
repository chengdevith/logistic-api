package com.logistic.logistic.features.auth;

import com.logistic.logistic.features.auth.dto.JwtResponse;
import com.logistic.logistic.features.auth.dto.LoginRequest;
import com.logistic.logistic.features.auth.dto.RefreshTokenRequest;
import com.logistic.logistic.features.auth.dto.RegisterRequest;
import com.logistic.logistic.features.customer.dto.CustomerResponse;
import jakarta.validation.Valid;

public interface AuthService {
    JwtResponse login(@Valid LoginRequest loginRequest);
    JwtResponse refreshToken(@Valid RefreshTokenRequest refreshTokenRequest);
    CustomerResponse registerUser(@Valid RegisterRequest registerRequest);
}
