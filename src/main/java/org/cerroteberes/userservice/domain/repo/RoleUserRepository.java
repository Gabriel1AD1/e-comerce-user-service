package org.cerroteberes.authservice.domain.repo;

import org.cerroteberes.authservice.domain.comons.ReadRepository;
import org.cerroteberes.authservice.domain.comons.WriteRepository;
import org.cerroteberes.authservice.domain.entity.RoleUser;

public interface RoleUserRepository extends WriteRepository<RoleUser,Long> , ReadRepository<RoleUser,Long> {

    RoleUser findByIdUserAndIdRole(Long idUser, Long roleId);

    int deleteByIdUserAndIdRole(Long idUser, Long roleId);
}
