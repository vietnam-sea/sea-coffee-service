package org.vietnamsea.database.user;

import org.vietnamsea.database.base.BaseEntity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "user_accounts")
@Getter
@Setter
@AllArgsConstructor
public class UserAccountEntity extends BaseEntity {
    
}
