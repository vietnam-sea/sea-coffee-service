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
import org.vietnamsea.authentication.service.impl.PartnerUserDetailServiceImpl;

import java.io.IOException;
import java.util.EnumMap;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class PartnerJwtAuthenticationFilter extends BaseJwtAuthenticationFilter {
    private final PartnerUserDetailServiceImpl partnerUserDetailService;
    public PartnerJwtAuthenticationFilter(JwtService jwtService, PartnerUserDetailServiceImpl partnerUserDetailService) {
        super(jwtService);
        this.partnerUserDetailService = partnerUserDetailService;
    }
    @Override
    protected void doFilterInternal(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull FilterChain filterChain) throws ServletException, IOException {
        var cookies = request.getCookies();
        EnumMap<JwtTokenType, Cookie> cookieMap = filterJwtCookie(cookies);
        AtomicReference<UserDetails> userDetailsAtomicReference = new AtomicReference<>();
        var userClaimsOptional =handleUserClaimsVerify(cookieMap, (claims) -> {
            try {
                var userDetails = partnerUserDetailService.loadUserByUsername(claims.getUsername());
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