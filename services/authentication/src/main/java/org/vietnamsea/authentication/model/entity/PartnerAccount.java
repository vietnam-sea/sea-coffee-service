package org.vietnamsea.authentication.model.entity;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.vietnamsea.database.base.BaseNoSQLEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "partner_accounts")
public class PartnerAccount extends BaseNoSQLEntity{
    @Field(name = "business_name")
    private String businessName;
    @Field(name = "email")
    private String email;
    @Field(name = "username")
    private String username;
    @Field(name = "hash_password")
    private String hashPassword;
    @Field(name = "description")
    private String description;
    @Field(name = "verification_documents")
    private String verificationDocuments;
    @Field(name =  "contact")
    private ContactInfo contactInfo;
    @Field(name = "services")
    private List<Service> services;
    @Field(name = "is_active")
    private boolean isActive;
    @Field(name = "is_looked")
    private boolean isLocked;
    @Field(name = "is_verified")
    private boolean isVerified;

    @Data
    public static class ContactInfo {
        private List<String> phone;
        private List<String> website;
    }

    @Data
    public static class Service {
        private String serviceId;
        private String name;
        private String description;
        private double price;
    }
}
