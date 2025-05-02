package org.vietnamsea.authentication.repository;

import org.springframework.stereotype.Repository;
import org.vietnamsea.authentication.model.entity.CustomerAccountEntity;
import org.vietnamsea.common.repository.BaseJpaRepository;

@Repository
public interface CustomerAccountRepository extends BaseJpaRepository<CustomerAccountEntity, String> {
}
