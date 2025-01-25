package org.cerroteberes.authservice.app.implementation.usecase;

import lombok.AllArgsConstructor;
import org.cerroteberes.authservice.app.implementation.service.role.RoleService;
import org.cerroteberes.authservice.app.implementation.service.role_user.RoleUserService;
import org.cerroteberes.authservice.app.implementation.service.user.UserService;
import org.cerroteberes.authservice.app.mapper.dto.RequestCreateUserMapper;
import org.cerroteberes.authservice.app.port.input.use_case.InCreateUser;
import org.cerroteberes.authservice.app.port.input.use_case.InDeleteUser;
import org.cerroteberes.authservice.app.port.input.use_case.InListUser;
import org.cerroteberes.authservice.app.port.input.use_case.InUpdateUser;
import org.cerroteberes.authservice.app.port.output.annotation.AppUseCase;
import org.cerroteberes.authservice.domain.dto.request.RequestCreateUserDTO;
import org.cerroteberes.authservice.domain.entity.User;

@AppUseCase
@AllArgsConstructor
public class UserUseCase implements InCreateUser, InDeleteUser, InListUser, InUpdateUser {
    private final RequestCreateUserMapper requestCreateUserMapper;
    private final UserService userService;
    private final RoleService roleService;
    private final RoleUserService roleUserService;
    @Override
    public Long execute(RequestCreateUserDTO dto) {
        User user = requestCreateUserMapper.toEntity(dto);
        return userService.create(user).getId();
    }
}
