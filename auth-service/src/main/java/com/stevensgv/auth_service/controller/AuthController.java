package com.stevensgv.auth_service.controller;

import com.stevensgv.auth_service.dto.AuthLoginRequestDTO;
import com.stevensgv.auth_service.dto.AuthResponseDTO;
import com.stevensgv.auth_service.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/login")
public class AuthController {

    private final AuthService authService;

    @PostMapping
    public AuthResponseDTO login (@Valid @RequestBody AuthLoginRequestDTO request) {
        return authService.login(request);
    }
}
