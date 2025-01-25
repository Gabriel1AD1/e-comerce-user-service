package org.cerroteberes.userservice.app.implementation.usecase;

import lombok.AllArgsConstructor;
import org.cerroteberes.userservice.app.implementation.service.role.RoleService;
import org.cerroteberes.userservice.app.implementation.service.role_user.RoleUserService;
import org.cerroteberes.userservice.app.implementation.service.user.UserService;
import org.cerroteberes.userservice.app.mapper.dto.RequestUserMapper;
import org.cerroteberes.userservice.app.port.input.use_case.InCreateUser;
import org.cerroteberes.userservice.app.port.input.use_case.InDeleteUser;
import org.cerroteberes.userservice.app.port.input.use_case.InListUser;
import org.cerroteberes.userservice.app.port.input.use_case.InUpdateUser;
import org.cerroteberes.userservice.app.port.output.annotation.AppUseCase;
import org.cerroteberes.userservice.domain.dto.request.RequestCreateUserDTO;
import org.cerroteberes.userservice.domain.dto.request.RequestUpdateUserDTO;
import org.cerroteberes.userservice.domain.dto.response.ReadUserDTO;
import org.cerroteberes.userservice.domain.entity.User;

import java.util.List;

@AppUseCase
@AllArgsConstructor
public class UserUseCase implements InCreateUser, InDeleteUser, InListUser, InUpdateUser {
    private final RequestUserMapper requestUserMapper;
    private final UserService userService;
    @Override
    public User execute(RequestCreateUserDTO dto) {
        User user = requestUserMapper.toEntity(dto);
        return userService.create(user);
    }

    @Override
    public void execute(Long id) {
        userService.delete(id);
    }

    @Override
    public List<ReadUserDTO> execute() {
        return userService.getAll();
    }

    @Override
    public void execute(RequestUpdateUserDTO dto,Long id) {
        User user = requestUserMapper.toEntity(dto);
        userService.update(user,id);
    }
}
