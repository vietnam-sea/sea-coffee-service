package org.vietnamsea.authentication.repository;

import org.springframework.stereotype.Repository;
import org.vietnamsea.authentication.model.entity.AccountEntity;
import org.vietnamsea.common.repository.BaseMongoRepository;

import java.util.Optional;

@Repository
public interface CustomerAccountRepository extends BaseMongoRepository<AccountEntity, String> {
    Optional<AccountEntity> findByUsername(String username);
}
