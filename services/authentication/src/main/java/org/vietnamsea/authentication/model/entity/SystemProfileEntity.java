package org.vietnamsea.authentication.model.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

@Data
public class SystemProfileEntity {
    @Field(name = "full_name", targetType = FieldType.STRING)
    private String fullName;
}
