package org.vietnamsea.authentication.model.other;

import java.math.BigDecimal;

import org.vietnamsea.authentication.model.constant.JwtTokenType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserClaims {
    private BigDecimal id;
    private String role;
    private JwtTokenType tokenType;
    private String username;
}
