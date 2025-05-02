package org.vietnamsea.authentication.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@Configuration
public class JwtConfig {
    @Value("${token.jwt.access-token.ex-time}")
    private int jwtExpiration;
    @Value("${token.jwt.refresh-token.ex-time}")
    private int jwtRefreshExpiration;
    @Value("${token.jwt.access-token.key}")
    private String jwtAccessTokenKey;
    @Value("${token.jwt.refresh-token.key}")
    private String jwtRefreshTokenKey;
}
