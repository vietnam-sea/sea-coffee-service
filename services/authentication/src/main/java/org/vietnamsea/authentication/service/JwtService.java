package org.vietnamsea.authentication.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.vietnamsea.authentication.model.constant.JwtTokenType;
import org.vietnamsea.authentication.model.other.UserClaims;

import java.util.EnumMap;
import java.util.Optional;

public interface JwtService {
    Optional<UserClaims> getUserClaimsFromJwt(String token, JwtTokenType tokenType);

    Optional<UserClaims> getUserClaimsFromJwt(EnumMap<JwtTokenType, Cookie> cookieEnumMap);

    Cookie tokenCookieWarp(String token, JwtTokenType tokenType);

    void removeAuthToken (HttpServletRequest request, HttpServletResponse response);

    void removeAuthToken(Cookie cookie, HttpServletResponse response);

    boolean isTokenValid(String token, JwtTokenType tokenType);
}
