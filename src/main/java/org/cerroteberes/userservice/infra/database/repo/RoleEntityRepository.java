package org.cerroteberes.userservice.infra.database.repo;

import org.cerroteberes.userservice.domain.entity.enums.NameRole;
import org.cerroteberes.userservice.infra.database.entity.RoleEntity;
import org.cerroteberes.userservice.infra.database.info.RoleEntityInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RoleEntityRepository extends JpaRepository<RoleEntity, Long> {
    @Query("select r.id,r.nameRole,r.descriptionRole from RoleEntity r where r.id = ?1")
    Optional<RoleEntityInfo> readFindById(Long id);

    @Query("select r.id,r.nameRole,r.descriptionRole from RoleEntity r")
    List<RoleEntityInfo> readFindAll();

    RoleEntity findByNameRole(NameRole nameRole);
}