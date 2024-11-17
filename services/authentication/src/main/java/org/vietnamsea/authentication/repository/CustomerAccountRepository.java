package org.vietnamsea.authentication.repository;

import org.springframework.stereotype.Repository;
import org.vietnamsea.authentication.model.entity.CustomerAccount;
import org.vietnamsea.database.base.BaseNoSQLRepository;

import java.math.BigDecimal;

@Repository
public interface CustomerAccountRepository extends BaseNoSQLRepository<CustomerAccount, BigDecimal> {
    
}
