package org.vietnamsea.database.base;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
public class BaseNoSQLEntity implements Serializable {
    @Id
    private String id;
    @Field(name = "created_date")
    private LocalDateTime createdDate;
    @Field(name = "updated_date")
    private LocalDateTime updatedDate;
    public BaseNoSQLEntity() {
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
    }
}
