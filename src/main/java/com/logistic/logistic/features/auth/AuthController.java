package com.logistic.logistic.features.auth;

import com.logistic.logistic.domain.Customer;
import com.logistic.logistic.features.auth.dto.JwtResponse;
import com.logistic.logistic.features.auth.dto.LoginRequest;
import com.logistic.logistic.features.auth.dto.RefreshTokenRequest;
import com.logistic.logistic.features.auth.dto.RegisterRequest;
import com.logistic.logistic.features.customer.dto.CustomerResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/refresh")
    JwtResponse refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest){
        return authService.refreshToken(refreshTokenRequest);
    }

    @PostMapping("/login")
    JwtResponse login(@Valid @RequestBody LoginRequest loginRequest){
        return authService.login(loginRequest);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    CustomerResponse registerUser(@Valid @RequestBody RegisterRequest registerRequest){
        return authService.registerUser(registerRequest);
    }
}
