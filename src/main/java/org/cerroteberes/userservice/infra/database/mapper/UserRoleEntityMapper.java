package org.cerroteberes.userservice.infra.database.mapper;

import org.cerroteberes.userservice.domain.entity.UserRole;
import org.cerroteberes.userservice.infra.database.entity.UserRoleEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserRoleEntityMapper {
    UserRoleEntity toEntity(UserRole userRole);

    UserRole toDomain(UserRoleEntity userRoleEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UserRoleEntity partialUpdate(UserRole userRole, @MappingTarget UserRoleEntity userRoleEntity);

    List<UserRole> toDomainList(List<UserRoleEntity> all);
}