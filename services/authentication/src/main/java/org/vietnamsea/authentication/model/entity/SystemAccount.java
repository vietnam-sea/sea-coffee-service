package org.vietnamsea.authentication.model.entity;

import lombok.*;
import org.vietnamsea.authentication.model.constant.SystemAccountRole;
import org.vietnamsea.database.base.BaseSQLEntity;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SystemAccount extends BaseSQLEntity {
    private String username;
    private String hashPassword;
    private SystemAccountRole role;
}
