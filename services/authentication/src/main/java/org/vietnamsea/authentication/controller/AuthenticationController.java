package org.vietnamsea.authentication.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vietnamsea.authentication.model.dto.request.AuthenticationRequest;
import org.vietnamsea.authentication.model.dto.request.ExternalCustomerAccountAuthRequest;
import org.vietnamsea.authentication.service.AuthService;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "auth")
public class AuthenticationController {
    private final AuthService authService;

    @PostMapping("customer/internal")
    public ResponseEntity<?> customerAuthenticationInternal(@Valid @RequestBody AuthenticationRequest request) {
        return null;
    }
    @PostMapping("customer/external")
    public ResponseEntity<?> customerAuthenticationExternal(@Valid @RequestBody ExternalCustomerAccountAuthRequest request) {
        return null;
    }
}
