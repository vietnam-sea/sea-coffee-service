package org.vietnamsea.database.base;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseNoSQLRepository <T extends BaseNoSQLEntity, ID> extends MongoRepository<T, ID> {
    
}
