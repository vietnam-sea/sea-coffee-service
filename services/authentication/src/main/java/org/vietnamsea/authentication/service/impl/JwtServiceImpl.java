package org.vietnamsea.authentication.service.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.vietnamsea.authentication.config.JwtConfig;
import org.vietnamsea.authentication.model.constant.JwtTokenType;
import org.vietnamsea.authentication.model.other.UserClaims;
import org.vietnamsea.authentication.service.JwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {
    private final JwtConfig jwtConfig;

    private Claims generateClaims (UserClaims userClaims) {
        Claims claims = Jwts.claims();
        claims.put("user", userClaims);
        return claims;
    }

    public String generateToken (Authentication authentication, JwtTokenType tokenType) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        var roles = userDetails.getAuthorities().toArray(new GrantedAuthority[0]);
        var role = roles[0];

        return generateToken(userDetails.getUsername(), role.getAuthority(), tokenType);
    }
    public String generateToken(String username, String role, JwtTokenType tokenType) {
        Date currentDate = new Date(System.currentTimeMillis());
        Date expiryDate = null;
        if(tokenType == JwtTokenType.ACCESS_TOKEN) {
            expiryDate = new Date(currentDate.getTime() + jwtConfig.getJwtExpiration());
        }else if (tokenType == JwtTokenType.REFRESH_TOKEN) {
            expiryDate = new Date(currentDate.getTime() + jwtConfig.getJwtRefreshExpiration());
        }
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(currentDate)
                .setClaims(generateClaims(UserClaims.builder()
                .username(username)
                .role(role)
                .tokenType(tokenType)
                .build()))
                .setExpiration(expiryDate)
                .signWith(getSigningKey(tokenType))
                .compact();
    }

    private SecretKey getSigningKey(JwtTokenType tokenType) {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(tokenType == JwtTokenType.ACCESS_TOKEN ? jwtConfig.getJwtAccessTokenKey() : jwtConfig.getJwtRefreshTokenKey()));
    }

    public UserClaims getUserClaimsFromJwt(String token, JwtTokenType tokenType) {
        var claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey(tokenType))
                .build()
                .parseClaimsJws(token).getBody();
        @SuppressWarnings("unchecked")
        Map<String, Object> userClaimsMap = (Map<String, Object>) claims.get("user");
        return convertMapToUserClaims(userClaimsMap);
    }

    private UserClaims convertMapToUserClaims(Map<String, Object> map) {
        BigDecimal id = (BigDecimal) map.get("id");
        String username = (String) map.get("username");
        String role = (String) map.get("role");
        JwtTokenType tokenType = JwtTokenType.valueOf((String) map.get("tokenType"));
    
        return UserClaims.builder()
                .id(id)
                .username(username)
                .role(role)
                .tokenType(tokenType)
                .build();
    }
}
