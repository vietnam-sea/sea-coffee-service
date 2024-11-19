package org.vietnamsea.authentication.security;

import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.vietnamsea.authentication.model.constant.JwtTokenType;
import org.vietnamsea.authentication.model.other.UserClaims;
import org.vietnamsea.authentication.service.JwtService;
import org.vietnamsea.authentication.service.impl.PartnerUserDetailServiceImpl;

import java.io.IOException;
import java.util.EnumMap;

@RequiredArgsConstructor
@Component
public class PartnerJwtAuthenticationFilter extends BaseJwtAuthenticationFilter {
    private final JwtService jwtService;
    private final PartnerUserDetailServiceImpl partnerUserDetailService;
    @Override
    protected void doFilterInternal(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull FilterChain filterChain) throws ServletException, IOException {
        var cookies = request.getCookies();
        EnumMap<JwtTokenType, Cookie> cookieMap = filterJwtCookie(cookies);
        var accessCookie = cookieMap.get(JwtTokenType.ACCESS_TOKEN);
        if (accessCookie != null) {
            var token = accessCookie.getValue();
            UserClaims claims = jwtService.getUserClaimsFromJwt(token, JwtTokenType.ACCESS_TOKEN);
            var userDetails = partnerUserDetailService.loadUserByUsername(claims.getUsername());
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }else {
            var refreshCookie = cookieMap.get(JwtTokenType.REFRESH_TOKEN);
            var refreshToken = refreshCookie.getValue();
            UserClaims claims = jwtService.getUserClaimsFromJwt(refreshToken, JwtTokenType.REFRESH_TOKEN);
            var userDetails = partnerUserDetailService.loadUserByUsername(claims.getUsername());
            addSecurityAuthentication(userDetails,request);
            var accessToken = jwtService.generateToken(SecurityContextHolder.getContext().getAuthentication(), JwtTokenType.ACCESS_TOKEN);
            var newAccessCookie = jwtService.cookieWarpToken(accessToken, JwtTokenType.ACCESS_TOKEN);
            response.addCookie(newAccessCookie);
        }
        filterChain.doFilter(request, response);
    }

}