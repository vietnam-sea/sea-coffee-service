package org.vietnamsea.authentication.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;
import org.vietnamsea.authentication.model.constant.AuthType;
import org.vietnamsea.common.model.entity.BaseSQLEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "customer_accounts")
public class CustomerAccountEntity extends BaseSQLEntity {
    @Column(name = "auth_type")
    private AuthType authType;
    @Field(name = "hash_password")
    private String hashPassword;
}
