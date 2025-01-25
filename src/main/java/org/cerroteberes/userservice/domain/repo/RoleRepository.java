package org.cerroteberes.authservice.domain.repo;

import org.cerroteberes.authservice.domain.comons.ReadRepository;
import org.cerroteberes.authservice.domain.comons.WriteRepository;
import org.cerroteberes.authservice.domain.dto.response.ReadRoleDTO;
import org.cerroteberes.authservice.domain.entity.Role;

import java.util.List;

public interface RoleRepository extends WriteRepository<Role,Long>, ReadRepository<ReadRoleDTO,Long> {
    List<Role> findAll();

}
