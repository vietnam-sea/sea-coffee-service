package org.vietnamsea.authentication.repository;

import org.springframework.stereotype.Repository;
import org.vietnamsea.authentication.model.entity.CustomerEntity;
import org.vietnamsea.database.base.BaseNoSQLRepository;

@Repository
public interface CustomerProfileRepository extends BaseNoSQLRepository<CustomerEntity, String> {
}
