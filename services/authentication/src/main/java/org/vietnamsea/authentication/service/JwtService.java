package org.vietnamsea.authentication.service;

import jakarta.servlet.http.Cookie;
import org.springframework.security.core.Authentication;
import org.vietnamsea.authentication.model.constant.JwtTokenType;
import org.vietnamsea.authentication.model.other.UserClaims;

public interface JwtService {
    String generateToken (Authentication authentication, JwtTokenType tokenType);
    String generateToken(String username, String role, JwtTokenType tokenType);
    UserClaims getUserClaimsFromJwt(String token, JwtTokenType tokenType);
    Cookie cookieWarpToken (String token, JwtTokenType tokenType);
}
