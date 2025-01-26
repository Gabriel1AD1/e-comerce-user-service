package org.cerroteberes.userservice.app.implementation.service.user;

import lombok.AllArgsConstructor;
import org.cerroteberes.userservice.app.exception.EntityNotFoundException;
import org.cerroteberes.userservice.app.port.output.annotation.AppService;
import org.cerroteberes.userservice.domain.dto.response.ReadUserDTO;
import org.cerroteberes.userservice.domain.entity.User;
import org.cerroteberes.userservice.domain.repo.UserRepository;

import java.time.Instant;
import java.util.List;

@AppService
@AllArgsConstructor
public class UserServiceI implements UserService {
    private final UserRepository userRepository;
    @Override
    public ReadUserDTO readFindById(Long id) {
        return userRepository.findByIdRead(id).orElseThrow(
                () -> new EntityNotFoundException("El usuario buscado no ha sido encontrado")
        );
    }

    @Override
    public List<ReadUserDTO> getAll() {
        return userRepository.findALlRead();
    }

    @Override
    public User create(User dto) {
        return userRepository.save(dto);
    }

    @Override
    public void update(User dto,Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("No se ha encontrado el usuario que desea actualizar")
        );
        user.setPassword(dto.getPassword());
        user.setName(dto.getName());
        user.setUpdateAt(Instant.now());
        userRepository.save(dto);
    }

    @Override
    public void delete(Long idUser) {
        userRepository.delete(idUser);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Usuario no encontrado")
        );
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new EntityNotFoundException("Usuario no encontrado por email")
        );
    }


}
