package org.cerroteberes.authservice.app.implementation.service.user;

import org.cerroteberes.authservice.domain.dto.request.RequestCreateUserDTO;
import org.cerroteberes.authservice.domain.dto.request.RequestUpdateUserDTO;
import org.cerroteberes.authservice.domain.dto.response.ReadUserDTO;
import org.cerroteberes.authservice.domain.entity.User;

import java.util.List;

public interface UserService {
    ReadUserDTO findById(Long id);
    List<ReadUserDTO> getAll();
    User create(User dto);
    void update(User dto,Long userId);
    void delete(Long idUser);
}
