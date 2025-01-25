package org.cerroteberes.authservice.app.implementation.service.role;

import org.cerroteberes.authservice.domain.dto.response.ReadRoleDTO;
import org.cerroteberes.authservice.domain.entity.Role;

import java.util.List;

public interface RoleService {
    Role create(Role role);
    Role update(Role role);
    void delete(Long id);
    List<ReadRoleDTO> FindALlRead();
    List<Role> findAll();
}
