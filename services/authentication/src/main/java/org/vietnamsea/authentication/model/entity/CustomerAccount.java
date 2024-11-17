package org.vietnamsea.authentication.model.entity;

import java.util.List;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Field.Write;
import org.vietnamsea.database.base.BaseNoSQLEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Document(collection = "customer_accounts")
public class CustomerAccount extends BaseNoSQLEntity {
    @Indexed(unique = true)
    @Field(name = "username", write = Write.ALWAYS)
    private String username;
    @Field(name = "hash_password", write = Write.ALWAYS)
    private String hashPassword;
    @Field(name = "avatars")
    private List<String> avatars;
    @Field(name = "roles", write = Write.ALWAYS)
    private List<String> roles;
    @Field(name = "google_id")
    private String googleId;
    @Field(name = "is_active", write = Write.ALWAYS)
    private boolean isActive;
    @Field(name = "is_verified", write = Write.ALWAYS)
    private boolean isVerified;
    @Field(name = "is_locked", write = Write.ALWAYS)
    private boolean isLocked;
}
