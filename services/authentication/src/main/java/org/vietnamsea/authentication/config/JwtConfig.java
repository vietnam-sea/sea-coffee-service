package org.vietnamsea.authentication.config;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class JwtConfig {
    private int jwtExpiration;
    private int jwtRefreshExpiration;
    private String jwtAccessTokenKey;
    private String jwtRefreshTokenKey;
}
