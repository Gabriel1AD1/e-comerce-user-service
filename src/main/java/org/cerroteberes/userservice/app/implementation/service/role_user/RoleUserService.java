package org.cerroteberes.userservice.app.implementation.service.role_user;

import org.cerroteberes.userservice.domain.entity.UserRole;

public interface RoleUserService {
    UserRole create(Long idUser, Long roleId);

    UserRole update(Long idUser, Long roleId);

    void delete(Long idUser,Long roleId);
}
