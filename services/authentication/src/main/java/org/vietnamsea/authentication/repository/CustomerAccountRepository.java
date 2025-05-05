package org.vietnamsea.authentication.repository;

import org.springframework.stereotype.Repository;
import org.vietnamsea.authentication.model.entity.CustomerAccountEntity;
import org.vietnamsea.common.repository.BaseMongoRepository;

import java.util.Optional;

@Repository
public interface CustomerAccountRepository extends BaseMongoRepository<CustomerAccountEntity, String> {
    Optional<CustomerAccountEntity> findByUsername(String username);
}
