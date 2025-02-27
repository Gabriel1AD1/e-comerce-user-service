package org.cerroteberes.userservice.app.implementation.service.role_user;

import org.cerroteberes.userservice.domain.entity.UserRole;
import org.cerroteberes.userservice.domain.entity.enums.NameRole;

import java.util.List;

public interface RoleUserService {
    UserRole create(Long idUser, Long roleId);

    UserRole update(Long idUser, Long roleId);

    void delete(Long idUser, Long roleId);

    List<NameRole> getRoleByUserId(Long userId);
}
