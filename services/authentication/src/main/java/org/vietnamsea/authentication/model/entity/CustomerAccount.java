package org.vietnamsea.authentication.model.entity;

import java.math.BigDecimal;
import java.sql.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Field.Write;

import lombok.Builder;
import lombok.Data;

@Document("customer_accounts")
@Data
@Builder
public class CustomerAccount {
    @Id
    private BigDecimal id;
    @Indexed(unique = true)
    private String username;
    @Field(name = "hash_password", write = Write.ALWAYS)
    private String hashPassword;
    @Field(name = "active")
    private boolean isActive;
    @Field(name = "verify")
    private boolean isVerified;
    @Field(name = "created_date")
    private Date createdDate;
    @Field(name = "updated_date")
    private Date updatedDate;
}
