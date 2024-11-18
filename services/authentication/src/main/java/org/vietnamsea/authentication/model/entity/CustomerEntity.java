package org.vietnamsea.authentication.model.entity;

import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Field.Write;
import org.vietnamsea.database.base.BaseNoSQLEntity;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Document(collection = "customer_profile")
public class CustomerEntity extends BaseNoSQLEntity {
    @Field(name = "first_name")
    private String firstName;
    @Field(name = "last_name")
    private String lastName;
    @Field(name = "email")
    private String email;
    @Field(name = "avatars")
    private List<String> avatars;
    @Field(name = "roles", write = Write.ALWAYS)
    private List<String> roles;
    @Field(name = "is_active", write = Write.ALWAYS)
    private boolean isActive;
    @Field(name = "is_verified", write = Write.ALWAYS)
    private boolean isVerified;
    @Field(name = "is_locked", write = Write.ALWAYS)
    private boolean isLocked;
}
