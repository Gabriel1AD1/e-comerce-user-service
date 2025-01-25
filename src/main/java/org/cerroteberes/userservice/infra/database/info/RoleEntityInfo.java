package org.cerroteberes.userservice.infra.database.info;

import org.cerroteberes.userservice.domain.entity.enums.NameRole;

/**
 * Projection for {@link org.cerroteberes.userservice.infra.database.entity.RoleEntity}
 */
public interface RoleEntityInfo {
    Long getId();

    NameRole getNameRole();

    String getDescriptionRole();
}