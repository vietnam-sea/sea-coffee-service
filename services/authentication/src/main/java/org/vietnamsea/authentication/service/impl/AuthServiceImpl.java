package org.vietnamsea.authentication.service.impl;

import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.vietnamsea.authentication.model.dto.request.AuthenticationRequest;
import org.vietnamsea.authentication.model.dto.response.AuthResponse;
import org.vietnamsea.authentication.service.AuthService;
import org.vietnamsea.authentication.service.JwtService;
import org.vietnamsea.authentication.util.AuthUtils;
import org.vietnamsea.authentication.util.QRUtils;
import org.vietnamsea.authentication.util.TokenUtils;
import org.vietnamsea.common.model.exception.ValidationException;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final AuthUtils authUtils;
    public AuthResponse localAuthentication(AuthenticationRequest authenticationRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return null;
    }
    public AuthResponse localAuthentication(AuthenticationRequest authenticationRequest, Consumer<List<Cookie>> callback) {
        return localAuthentication(authenticationRequest);
    }

    @Override
    public BufferedImage register2FaAuthentication(int width, int height) {
        var account = authUtils.getUserAccountFromAuthentication();
        var otpText = TokenUtils.generateOTPValue(account.getSecret(), account.getUsername(), "VietnamSea");
        return QRUtils.generateQRCode(otpText, width, height);
    }
    public void verify2FaAuthentication(String totp) {
        var account = authUtils.getUserAccountFromAuthentication();
        var totpSystem = TokenUtils.getTOTPCode(account.getSecret());
        if (!totpSystem.equals(totp)) {
            throw new ValidationException("Invalid OTP");
        }
    }
}
