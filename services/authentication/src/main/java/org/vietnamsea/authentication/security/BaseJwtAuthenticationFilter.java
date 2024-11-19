package org.vietnamsea.authentication.security;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import org.vietnamsea.authentication.model.constant.JwtTokenType;

import java.util.Arrays;
import java.util.EnumMap;

public abstract class BaseJwtAuthenticationFilter extends OncePerRequestFilter {
    protected EnumMap<JwtTokenType, Cookie> filterJwtCookie (Cookie[] cookies) {
        EnumMap<JwtTokenType, Cookie> cookieMap = new EnumMap<>(JwtTokenType.class);
        Arrays.stream(cookies).forEach(cookie -> cookieMap.put(JwtTokenType.valueOf(cookie.getName()), cookie));
        return cookieMap;
    }
    protected void addSecurityAuthentication (UserDetails userDetails, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
}
