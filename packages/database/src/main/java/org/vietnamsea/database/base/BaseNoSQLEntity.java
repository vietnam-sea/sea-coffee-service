package org.vietnamsea.database.base;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Field;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseNoSQLEntity implements Serializable {
    @Id
    private BigDecimal id;
    @Field(name = "created_date")
    private LocalDateTime createdDate;
    @Field(name = "updated_date")
    private LocalDateTime updatedDate;
}
