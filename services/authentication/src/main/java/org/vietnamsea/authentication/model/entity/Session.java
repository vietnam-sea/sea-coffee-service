package org.vietnamsea.authentication.model.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.vietnamsea.database.base.BaseNoSQLEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "sessions")
public class Session extends BaseNoSQLEntity {
    @Field(name = "user_id", write = Field.Write.ALWAYS)
    private String userId;
    @Field(name = "ip_address")
    private String ipAddress;
    @Field(name = "user_agent")
    private String userAgent;
}
