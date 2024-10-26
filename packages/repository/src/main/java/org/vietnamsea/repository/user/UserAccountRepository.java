package org.vietnamsea.repository.user;

import java.math.BigDecimal;

import org.springframework.stereotype.Repository;
import org.vietnamsea.database.user.UserAccountEntity;
import org.vietnamsea.repository.base.BaseRepository;

@Repository
public interface UserAccountRepository extends BaseRepository<UserAccountEntity, BigDecimal>{
    
}
