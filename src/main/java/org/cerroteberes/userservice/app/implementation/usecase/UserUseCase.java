package org.cerroteberes.userservice.app.implementation.usecase;

import lombok.AllArgsConstructor;
import org.cerroteberes.userservice.app.implementation.service.role.RoleService;
import org.cerroteberes.userservice.app.implementation.service.role_user.RoleUserService;
import org.cerroteberes.userservice.app.implementation.service.user.UserService;
import org.cerroteberes.userservice.app.mapper.dto.RequestUserMapper;
import org.cerroteberes.userservice.app.port.input.use_case.*;
import org.cerroteberes.userservice.app.port.output.annotation.AppUseCase;
import org.cerroteberes.userservice.domain.dto.request.RequestCreateUserDTO;
import org.cerroteberes.userservice.domain.dto.request.RequestUpdateUserDTO;
import org.cerroteberes.userservice.domain.dto.response.ReadUserDTO;
import org.cerroteberes.userservice.domain.dto.response.ResponseUserPrincipalDTO;
import org.cerroteberes.userservice.domain.entity.Role;
import org.cerroteberes.userservice.domain.entity.User;
import org.cerroteberes.userservice.domain.entity.enums.NameRole;
import org.cerroteberes.userservice.domain.entity.enums.TypeUserSignup;

import java.util.List;

@AppUseCase
@AllArgsConstructor
public class UserUseCase implements InCreateUser, InDeleteUser, InListUser, InUpdateUser, InGetUserPrincipalForEmail ,InGetUserPrincipalForUserId{
    private final RequestUserMapper requestUserMapper;
    private final UserService userService;
    private final RoleService roleService;
    private final RoleUserService roleUserService;
    @Override
    public User executeCreateUser(RequestCreateUserDTO dto, TypeUserSignup typeUserSignup) {
        User user = requestUserMapper.toEntity(dto);
        User userSave= userService.create(user);
        switch (typeUserSignup){
            case vendor -> {
                Role role = roleService.findByRoleName(NameRole.VENDOR);;
                roleUserService.create(userSave.getId(), role.getId());
            }
            case client -> {
                Role role = roleService.findByRoleName(NameRole.CLIENT);;
                roleUserService.create(userSave.getId(), role.getId());
            }
        }
        return userSave;
    }

    @Override
    public void executeDeleteUser(Long id) {
        userService.delete(id);
    }

    @Override
    public List<ReadUserDTO> executeListUser() {
        return userService.getAll();
    }

    @Override
    public void execute(RequestUpdateUserDTO dto,Long id) {
        User user = requestUserMapper.toEntity(dto);
        userService.update(user,id);
    }

    @Override
    public ResponseUserPrincipalDTO executeGetUserPrincipalForEmail(String email) {
        User user = userService.findByEmail(email);
        List<NameRole> roles = roleUserService.getRoleByUserId(user.getId());
        return ResponseUserPrincipalDTO.builder()
                .userId(user.getId())
                .passwordEncoded(user.getPassword())
                .roles(roles)
                .build();
    }
    @Override
    public ResponseUserPrincipalDTO executeGetUserPrincipalForUserId(Long userId){
        User user = userService.findById(userId);
        List<NameRole> roles = roleUserService.getRoleByUserId(user.getId());
        return ResponseUserPrincipalDTO.builder()
                .userId(user.getId())
                .passwordEncoded(user.getPassword())
                .roles(roles)
                .build();
    }
}
