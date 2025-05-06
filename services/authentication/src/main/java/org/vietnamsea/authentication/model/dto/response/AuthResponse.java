package org.vietnamsea.authentication.model.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AuthResponse {
    private String accessToken;
    private String refreshToken;
    private List<String> roles;
    private boolean twoFA;
}
