package org.cerroteberes.userservice.app.implementation.service.user;

import org.cerroteberes.userservice.domain.dto.response.ReadUserDTO;
import org.cerroteberes.userservice.domain.entity.User;

import java.util.List;

public interface UserService {
    ReadUserDTO readFindById(Long id);
    List<ReadUserDTO> getAll();
    User create(User dto);
    void update(User dto,Long userId);
    void delete(Long idUser);

    User findById(Long id);

    User findByEmail(String email);
}
