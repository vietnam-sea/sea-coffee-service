package org.vietnamsea.common.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class BaseSQLEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;
    @Column(name = "created_date", nullable = false)
    private Timestamp createdDate;
    @Column(name = "updated_date", nullable = false)
    private Timestamp updatedDate;


    @PrePersist
    protected void onCreate() {
        createdDate = Timestamp.valueOf(LocalDateTime.now());
        updatedDate = Timestamp.valueOf(LocalDateTime.now());
        id = UUID.randomUUID().toString();
    }
    @PreUpdate
    protected void onUpdate() {
        updatedDate = Timestamp.valueOf(LocalDateTime.now());
    }

}
