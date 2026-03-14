package com.logistic.logistic.features.auth;

import com.logistic.logistic.domain.Customer;
import com.logistic.logistic.features.auth.dto.JwtResponse;
import com.logistic.logistic.features.auth.dto.LoginRequest;
import com.logistic.logistic.features.auth.dto.RefreshTokenRequest;
import com.logistic.logistic.features.auth.dto.RegisterRequest;
import com.logistic.logistic.features.customer.dto.CustomerResponse;
import com.logistic.logistic.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final AuthRepository authRepository;
    private final DaoAuthenticationProvider daoAuthenticationProvider;
    private final JwtEncoder jwtEncoder;
    private final PasswordEncoder passwordEncoder;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final String TOKEN_TYPE = "Bearer";
    private final CustomerMapper customerMapper;

    private JwtEncoder jwtEncoderRefreshToken;
    private JwtDecoder jwtDecoder;

    @Autowired
    @Qualifier("jwtEncoderRefreshToken")
    public void setJwtEncoderRefreshToken(JwtEncoder jwtEncoderRefreshToken) {
        this.jwtEncoderRefreshToken = jwtEncoderRefreshToken;
    }

    @Override
    public JwtResponse login(LoginRequest loginRequest) {
        Authentication auth = new UsernamePasswordAuthenticationToken(
                loginRequest.email(),
                loginRequest.password()
        );

        auth = daoAuthenticationProvider.authenticate(auth);
        String scope = auth.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        Instant now = Instant.now();
        String userEmail = auth.getName();

        // Create access token claim set
        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .id(auth.getName())
                .issuedAt(now)
                .issuer("web")
                .audience(List.of("nextjs", "reactjs"))
                .subject(userEmail)
                .expiresAt(now.plus(30, ChronoUnit.MINUTES))
                .claim("scope", scope)
                .claim("email",userEmail)
                .build();

        // Create access token claim set
        JwtClaimsSet jwtClaimsSetRefreshToken = JwtClaimsSet.builder()
                .id(auth.getName())
                .issuedAt(now)
                .issuer("web")
                .audience(List.of("nestjs", "reactjs"))
                .subject(userEmail)
                .expiresAt(now.plus(7, ChronoUnit.DAYS))
                .claim("email",userEmail)
                .build();

        // AccessToken
        JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(jwtClaimsSet);
        Jwt jwt = jwtEncoder.encode(jwtEncoderParameters);

        // RefreshToken
        JwtEncoderParameters jwtEncoderParameterRefreshToken = JwtEncoderParameters.from(jwtClaimsSetRefreshToken);
        Jwt jwtRefreshToken = jwtEncoderRefreshToken.encode(jwtEncoderParameterRefreshToken);

        String accessToken = jwt.getTokenValue();
        String refreshToken = jwtRefreshToken.getTokenValue();

        return JwtResponse.builder()
                .tokenType(TOKEN_TYPE)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public JwtResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {

        Authentication auth = new BearerTokenAuthenticationToken(refreshTokenRequest.token());
        auth = jwtAuthenticationProvider.authenticate(auth);

        String scops = auth.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        Instant now = Instant.now();
        Jwt jwt = (Jwt) auth.getPrincipal();

        String userEmail = jwt.getClaimAsString("email");
        if (userEmail == null) {
            userEmail = jwt.getId(); // fallback to ID if email claim doesn't exist
        }

        // Create access token claim set
        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .id(jwt.getId())
                .issuedAt(now)
                .issuer("web")
                .audience(List.of("nesxtjs", "reactjs"))
                .subject(userEmail)
                .expiresAt(now.plus(30, ChronoUnit.MINUTES))
                .claim("scope", scops)
                .claim("email",userEmail)
                .build();

        JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(jwtClaimsSet);
        Jwt encodedJwt = jwtEncoder.encode(jwtEncoderParameters);

        String accessToken = encodedJwt.getTokenValue();
        String refreshToken = refreshTokenRequest.token();

        if (Duration.between(Instant.now(), jwt.getExpiresAt()).toDays() < 2) {
            JwtClaimsSet jwtClaimsSetRefreshToken = JwtClaimsSet.builder()
                    .id(auth.getName())
                    .issuedAt(now)
                    .issuer("web")
                    .audience(List.of("nestjs", "reactjs"))
                    .subject(userEmail)
                    .expiresAt(now.plus(7, ChronoUnit.DAYS))
                    .claim("email",userEmail)
                    .build();
            JwtEncoderParameters jwtEncoderParameterRefreshToken = JwtEncoderParameters.from(jwtClaimsSetRefreshToken);
            Jwt jwtRefreshToken = jwtEncoderRefreshToken.encode(jwtEncoderParameterRefreshToken);
            refreshToken = jwtRefreshToken.getTokenValue();
        }
        return JwtResponse.builder()
                .tokenType(TOKEN_TYPE)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public CustomerResponse registerUser(RegisterRequest registerRequest) {
        if (authRepository.existsByName(registerRequest.name())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Username is already in use"
            );
        }
        if (authRepository.existsByEmail(registerRequest.email())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Email is already in use"
            );
        }
        if (!registerRequest.password().equals(registerRequest.confirmPassword())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Passwords do not match"
            );
        }

        Customer customer = customerMapper.fromRegisterRequest(registerRequest);
        customer.setCreatedAt(LocalDateTime.now());
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));

        Customer save = authRepository.save(customer);

        return customerMapper.toCustomerResponse(save);
    }
}
