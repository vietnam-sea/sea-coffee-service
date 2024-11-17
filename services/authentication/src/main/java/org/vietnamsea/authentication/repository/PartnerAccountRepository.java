package org.vietnamsea.authentication.repository;

import org.springframework.stereotype.Repository;
import org.vietnamsea.authentication.model.entity.PartnerAccount;
import org.vietnamsea.database.base.BaseNoSQLRepository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface PartnerAccountRepository extends BaseNoSQLRepository<PartnerAccount, BigDecimal> {
    Optional<PartnerAccount> findPartnerAccountByEmail(String email);
}
