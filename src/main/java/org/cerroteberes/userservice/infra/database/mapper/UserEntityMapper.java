package org.cerroteberes.userservice.infra.database.mapper;

import org.cerroteberes.userservice.domain.entity.User;
import org.cerroteberes.userservice.infra.database.entity.UserEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserEntityMapper {
    UserEntity toEntity(User user);

    User toDomain(UserEntity userEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UserEntity partialUpdate(User user, @MappingTarget UserEntity userEntity);
}