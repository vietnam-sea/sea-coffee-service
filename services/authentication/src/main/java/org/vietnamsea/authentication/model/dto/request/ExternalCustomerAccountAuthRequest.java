package org.vietnamsea.authentication.model.dto.request;

import lombok.Builder;
import lombok.Data;
import org.vietnamsea.authentication.model.constant.AuthType;

@Data
@Builder
public class ExternalCustomerAccountAuthRequest {
    private String uid;
    private AuthType authType;
    private String token;
}
