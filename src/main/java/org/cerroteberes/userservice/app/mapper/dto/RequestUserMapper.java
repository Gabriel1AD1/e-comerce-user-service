package org.cerroteberes.userservice.app.mapper.dto;

import org.cerroteberes.userservice.domain.dto.request.RequestCreateUserDTO;
import org.cerroteberes.userservice.domain.dto.request.RequestUpdateUserDTO;
import org.cerroteberes.userservice.domain.entity.User;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RequestUserMapper {
    User toEntity(RequestCreateUserDTO requestCreateUserDTO);

    RequestCreateUserDTO toDto(User user);

    User toEntity(RequestUpdateUserDTO requestUpdateUserDTO);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(RequestCreateUserDTO requestCreateUserDTO, @MappingTarget User user);
}