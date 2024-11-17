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

@Document(collection = "partner_accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PartnerAccount extends BaseNoSQLEntity{
    @Field(name = "business_name")
    private String businessName;
    @Field(name = "email")
    private String email;
    @Field(name = "description")
    private String description;
    @Field(name = "verification_documents")
    private String verificationDocuments;

    @Data
    public class ContactInfo {
        private List<String> phone;
        private List<String> website;
    }

    @Data
    public class Service {
        private String serviceId;
        private String name;
        private String description;
        private double price;
    }
}
