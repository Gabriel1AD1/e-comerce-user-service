package org.cerroteberes.userservice.infra.database.mapper;

import org.cerroteberes.userservice.domain.entity.Role;
import org.cerroteberes.userservice.infra.database.entity.RoleEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoleEntityMapper {
    RoleEntity toEntity(Role role);

    Role toDomain(RoleEntity roleEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    RoleEntity partialUpdate(Role role, @MappingTarget RoleEntity roleEntity);

    List<Role> toDomainList(List<RoleEntity> all);
}