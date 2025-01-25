package org.cerroteberes.authservice.app.implementation.service.role_user;

import org.cerroteberes.authservice.domain.entity.RoleUser;

public interface RoleUserService {
    RoleUser create(Long idUser, Long roleId);

    RoleUser update(Long idUser, Long roleId);

    void delete(Long idUser,Long roleId);
}
