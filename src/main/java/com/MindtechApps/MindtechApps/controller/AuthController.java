package com.MindtechApps.MindtechApps.controller;

import com.MindtechApps.MindtechApps.dto.UserDTO;
import com.MindtechApps.MindtechApps.dto.request.LoginRequest;
import com.MindtechApps.MindtechApps.dto.request.RegisterRequest;
import com.MindtechApps.MindtechApps.dto.response.JwtAuthResponse;
import com.MindtechApps.MindtechApps.entity.User;
import com.MindtechApps.MindtechApps.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;
    private final ConversionService conversionService;

    @PostMapping("/register")
    public JwtAuthResponse register(final @RequestBody RegisterRequest request) throws Exception{
        return authenticationService.register(request);
    }

    @PostMapping("/login")
    public JwtAuthResponse login(final @RequestBody LoginRequest request) {
        return authenticationService.login(request);
    }

    @GetMapping("/who-am-i")
    public UserDTO getCurrentUser(final @AuthenticationPrincipal User user) {
        return conversionService.convert(user, UserDTO.class);
    }
}
