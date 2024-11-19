package org.vietnamsea.authentication.security;

import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.vietnamsea.authentication.model.constant.JwtTokenType;
import org.vietnamsea.authentication.model.other.UserClaims;
import org.vietnamsea.authentication.service.JwtService;
import org.vietnamsea.authentication.service.impl.CustomerUserDetailServiceImpl;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomerJwtAuthenticationFilter extends BaseJwtAuthenticationFilter {
    private final JwtService jwtService;
    private final CustomerUserDetailServiceImpl customerUserDetailService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull FilterChain filterChain) throws ServletException, IOException {
        var cookies = request.getCookies();
        var cookieEnumMap = filterJwtCookie(cookies);
        var accessCookie = cookieEnumMap.get(JwtTokenType.ACCESS_TOKEN);
        if (accessCookie != null) {
            var token = accessCookie.getValue();
            UserClaims claims = jwtService.getUserClaimsFromJwt(token, JwtTokenType.ACCESS_TOKEN);
            var userDetails = customerUserDetailService.loadUserByUsername(claims.getUsername());
            addSecurityAuthentication(userDetails, request);
        }else {
            var refreshCookie = cookieEnumMap.get(JwtTokenType.REFRESH_TOKEN);
            var refreshToken = refreshCookie.getValue();
            UserClaims claims = jwtService.getUserClaimsFromJwt(refreshToken, JwtTokenType.REFRESH_TOKEN);
            var userDetails = customerUserDetailService.loadUserByUsername(claims.getUsername());
            addSecurityAuthentication(userDetails, request);
            var accessToken = jwtService.generateToken(SecurityContextHolder.getContext().getAuthentication(), JwtTokenType.ACCESS_TOKEN);
            var newAccessCookie = jwtService.cookieWarpToken(accessToken, JwtTokenType.ACCESS_TOKEN);
            response.addCookie(newAccessCookie);
        }
        filterChain.doFilter(request, response);
    }
}
