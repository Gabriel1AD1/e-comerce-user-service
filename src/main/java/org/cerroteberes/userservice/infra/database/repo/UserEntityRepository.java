package org.cerroteberes.userservice.infra.database.repo;

import org.cerroteberes.userservice.infra.database.entity.UserEntity;
import org.cerroteberes.userservice.infra.database.info.UserEntityInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
    @Query("select u.id,u.name from UserEntity u where u.id = ?1")
    Optional<UserEntityInfo> readFindById(Long id);

    @Query("select u.id,u.name from UserEntity u where u.id = ?1")
    List<UserEntityInfo> readFindAll();
}