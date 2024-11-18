package org.vietnamsea.authentication.model.entity;

import lombok.*;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.vietnamsea.authentication.model.constant.AuthType;
import org.vietnamsea.database.base.BaseNoSQLEntity;

@Document("customer_account")
@CompoundIndexes({
    @CompoundIndex(name = "uid_auth_type_customer_profile_id_idx", def = "{'uid': 1, 'auth_type': 1, 'customer_profile_id': 1}")
})
@Getter
@Setter
@AllArgsConstructor
@Builder
public class CustomerAccountEntity extends BaseNoSQLEntity {
    @Field(name = "uid")
    private String uid;
    @Field(name = "auth_type")
    private AuthType authType;
    @Field(name = "customer_profile_id")
    private String customerProfileId;
    @Field(name = "hash_password")
    private String hashPassword;
}
