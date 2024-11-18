package org.vietnamsea.authentication.repository;

import org.springframework.stereotype.Repository;
import org.vietnamsea.authentication.model.entity.SystemAccount;
import org.vietnamsea.database.base.BaseSQLRepository;

import java.math.BigDecimal;

@Repository
public interface SystemAccountRepository extends BaseSQLRepository<SystemAccount, BigDecimal> {
}
