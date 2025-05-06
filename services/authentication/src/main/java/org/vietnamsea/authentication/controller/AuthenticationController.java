package org.vietnamsea.authentication.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vietnamsea.authentication.model.dto.request.AuthenticationRequest;
import org.vietnamsea.authentication.model.dto.request.ExternalCustomerAccountAuthRequest;
import org.vietnamsea.authentication.model.dto.response.AuthResponse;
import org.vietnamsea.authentication.service.AuthService;
import org.vietnamsea.common.model.dto.response.ResponseObject;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "auth")
public class AuthenticationController {
    private final AuthService authService;

    @PostMapping("local")
    public ResponseEntity<ResponseObject<AuthResponse>> customerAuthenticationInternal(@Valid @RequestBody AuthenticationRequest request, HttpServletResponse response) {
        var result = authService.localAuthentication(request, (cookies -> {
            cookies.forEach(response::addCookie);
        } ));
        return ResponseEntity.ok(new ResponseObject.Builder<AuthResponse>()
                        .code("AUTH_SUCCESS")
                        .success(true)
                        .messages("Authentication successful")
                        .content(result)
                        .build()
        );
    }
    @PostMapping("external")
    public ResponseEntity<?> customerAuthenticationExternal(@Valid @RequestBody ExternalCustomerAccountAuthRequest request) {
        return null;
    }
}
