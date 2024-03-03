package com.MindtechApps.MindtechApps.service;

import com.MindtechApps.MindtechApps.dto.request.LoginRequest;
import com.MindtechApps.MindtechApps.dto.request.RegisterRequest;
import com.MindtechApps.MindtechApps.dto.response.JwtAuthResponse;
import com.MindtechApps.MindtechApps.entity.User;
import com.MindtechApps.MindtechApps.enums.Role;
import com.MindtechApps.MindtechApps.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public JwtAuthResponse register(final RegisterRequest request) throws Exception {
        User user = User
            .builder()
            .userName(request.getUserName())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(Role.CUSTOMER)
            .build();
        Optional<User> existingUser = userRepository.getUserByEmail(request.getEmail());
        if (existingUser.isPresent()) {
            throw new Exception("User already exists");
        }
        userRepository.save(user);
        String jwt = jwtService.generateToken(user);
        return JwtAuthResponse.builder().token(jwt).build();
    }

    public JwtAuthResponse login(final LoginRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        User user = userRepository.getUserByEmail(request.getEmail())
            .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        String jwt = jwtService.generateToken(user);
        return JwtAuthResponse.builder().token(jwt).build();
    }
}
