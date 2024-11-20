package org.vietnamsea.authentication.security;

import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.vietnamsea.authentication.model.constant.JwtTokenType;
import org.vietnamsea.authentication.service.JwtService;
import org.vietnamsea.authentication.service.impl.CustomerUserDetailServiceImpl;

import java.io.IOException;
import java.util.EnumMap;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class CustomerJwtAuthenticationFilter extends BaseJwtAuthenticationFilter {
    private final CustomerUserDetailServiceImpl customerUserDetailService;
    protected CustomerJwtAuthenticationFilter(
            JwtService jwtService,
            CustomerUserDetailServiceImpl customerUserDetailService
    ) {
        super(jwtService);
        this.customerUserDetailService = customerUserDetailService;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull FilterChain filterChain) throws ServletException, IOException {
        var cookies = request.getCookies();
        EnumMap<JwtTokenType, Cookie> cookieMap = filterJwtCookie(cookies);
        AtomicReference<UserDetails> userDetailsAtomicReference = new AtomicReference<>();
        var userClaimsOptional =handleUserClaimsVerify(cookieMap, (claims) -> {
            try {
                var userDetails = customerUserDetailService.loadUserByUsername(claims.getUsername());
                userDetailsAtomicReference.set(userDetails);
                return true;
            } catch (Exception e) {
                return false;
            }
        }, response);
        if (userClaimsOptional.isPresent() && userDetailsAtomicReference.get() != null) {
            addSecurityAuthentication(userDetailsAtomicReference.get(), request);
        }
        filterChain.doFilter(request, response);
    }
}
