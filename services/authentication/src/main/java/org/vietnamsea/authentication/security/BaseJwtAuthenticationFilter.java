package org.vietnamsea.authentication.security;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import org.vietnamsea.authentication.model.constant.JwtTokenType;
import org.vietnamsea.authentication.model.other.UserClaims;
import org.vietnamsea.authentication.service.JwtService;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class BaseJwtAuthenticationFilter extends OncePerRequestFilter {
    protected final JwtService jwtService;
    protected BaseJwtAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }
    protected EnumMap<JwtTokenType, Cookie> filterJwtCookie (Cookie[] cookies) {
        EnumMap<JwtTokenType, Cookie> cookieMap = new EnumMap<>(JwtTokenType.class);
        Arrays.stream(cookies).forEach(cookie -> cookieMap.put(JwtTokenType.valueOf(cookie.getName()), cookie));
        return cookieMap;
    }
    protected Optional<UserClaims> handleUserClaimsVerify (EnumMap<JwtTokenType, Cookie> cookieMap, Function<UserClaims, Boolean> callback, HttpServletResponse response ) {
        Cookie cookie = cookieMap.get(JwtTokenType.ACCESS_TOKEN);
        Cookie refreshCookie = cookieMap.get(JwtTokenType.REFRESH_TOKEN);
        if (cookie != null) {
            var token = cookie.getValue();
            return Optional.of(jwtService.getUserClaimsFromJwt(token, JwtTokenType.ACCESS_TOKEN));
        } else if (refreshCookie != null) {
            var token = refreshCookie.getValue();
            var claim = jwtService.getUserClaimsFromJwt(token, JwtTokenType.REFRESH_TOKEN);
            var isClaimsValid = callback.apply(claim);
            if (isClaimsValid) {
                var newAccessToken = jwtService.generateToken(claim.getUsername(), claim.getRole(), JwtTokenType.ACCESS_TOKEN);
                var newAccessCookie = jwtService.cookieWarpToken(newAccessToken, JwtTokenType.ACCESS_TOKEN);
                response.addCookie(newAccessCookie);
                return Optional.of(claim);
            }
        }
        return Optional.empty();
    }
    protected void addSecurityAuthentication (UserDetails userDetails, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
}
