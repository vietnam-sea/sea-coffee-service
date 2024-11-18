package org.vietnamsea.authentication.repository;

import org.springframework.stereotype.Repository;
import org.vietnamsea.authentication.model.constant.AuthType;
import org.vietnamsea.authentication.model.entity.CustomerAccountEntity;
import org.vietnamsea.database.base.BaseNoSQLRepository;

import java.util.Optional;

@Repository
public interface CustomerAccountRepository extends BaseNoSQLRepository<CustomerAccountEntity, String> {
    Optional<CustomerAccountEntity> findCustomerAccountEntityByUidAndAuthType(String uid, AuthType authType);
}
