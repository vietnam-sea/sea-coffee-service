package org.vietnamsea.authentication.model.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.vietnamsea.database.base.BaseNoSQLEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "sessions")
public class Session extends BaseNoSQLEntity {
    private String userId;
    private String ipAddress;
    private String userAgent;
}
