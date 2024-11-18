package org.vietnamsea.authentication.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.vietnamsea.authentication.model.constant.JwtTokenType;

import java.io.IOException;
import java.util.Arrays;
import java.util.EnumMap;

@Component
@RequiredArgsConstructor
public class CustomerJwtAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var cookies = request.getCookies();
        EnumMap<JwtTokenType, Cookie> cookieEnumMap = new EnumMap<>(JwtTokenType.class);
        Arrays.stream(cookies).forEach(cookie -> {
            var name = cookie.getName();
            if (JwtTokenType.valueOf(name) == JwtTokenType.ACCESS_TOKEN) {
                cookieEnumMap.put(JwtTokenType.ACCESS_TOKEN, cookie);
            }else {
                cookieEnumMap.put(JwtTokenType.REFRESH_TOKEN, cookie);
            }
        });
        cookieEnumMap.get(JwtTokenType.ACCESS_TOKEN);
    }
}
