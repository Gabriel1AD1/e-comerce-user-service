package org.cerroteberes.userservice.infra.database.repo;

import org.cerroteberes.userservice.domain.entity.enums.NameRole;
import org.cerroteberes.userservice.infra.database.entity.RoleEntity;
import org.cerroteberes.userservice.infra.database.entity.UserEntity;
import org.cerroteberes.userservice.infra.database.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRoleEntityRepository extends JpaRepository<UserRoleEntity, Long> {
    List<UserRoleEntity> User(UserEntity user);

    int deleteByUserAndRole(UserEntity build, RoleEntity build1);

    @Transactional
    @Modifying
    @Query("delete from UserRoleEntity u where u.user.id = ?1 and u.role.id = ?2")
    int deleteByUserIdAndRoleId(Long user_id, Long role_id);

    @Query("SELECT ur.role.nameRole FROM UserRoleEntity ur WHERE ur.user.id = :userId")
    List<NameRole> findRoleNamesByUserId(Long userId);
}