package org.cerroteberes.userservice.infra.database.adapter;

import lombok.AllArgsConstructor;
import org.cerroteberes.userservice.app.port.output.repo.OutRoleUserRepository;
import org.cerroteberes.userservice.domain.entity.UserRole;
import org.cerroteberes.userservice.infra.database.entity.RoleEntity;
import org.cerroteberes.userservice.infra.database.entity.UserEntity;
import org.cerroteberes.userservice.infra.database.entity.UserRoleEntity;
import org.cerroteberes.userservice.infra.database.mapper.UserRoleEntityMapper;
import org.cerroteberes.userservice.infra.database.repo.UserRoleEntityRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static org.cerroteberes.userservice.app.utils.OptionalMapper.wrapInOptional;

@Repository
@AllArgsConstructor
public class AdapterOutRoleUserRepository implements OutRoleUserRepository {
    private final UserRoleEntityRepository userRoleEntityRepository;
    private final UserRoleEntityMapper userRoleEntityMapper;
    @Override
    public Optional<UserRole> findByIdUserAndIdRole(Long idUser, Long roleId) {
        return wrapInOptional(userRoleEntityMapper.toDomain(
                userRoleEntityRepository.save(
                        UserRoleEntity.builder()
                                .user(UserEntity.builder().id(idUser).build())
                                .role(RoleEntity.builder().id(roleId).build())
                                .build()
                )
        ));
    }

    @Override
    public int deleteByIdUserAndIdRole(Long idUser, Long roleId) {
        return userRoleEntityRepository.deleteByUserIdAndRoleId(idUser,roleId);
    }


    @Override
    public UserRole save(UserRole entity) {
        return userRoleEntityMapper.toDomain(
                userRoleEntityRepository.save(userRoleEntityMapper.toEntity(entity))
        );
    }

    @Override
    public void delete(Long id) {
        userRoleEntityRepository.deleteById(id);
    }
}
