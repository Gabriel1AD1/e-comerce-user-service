package org.cerroteberes.authservice.infra.database.adapter;

import lombok.AllArgsConstructor;
import org.cerroteberes.authservice.app.port.output.repo.OutUserRepository;
import org.cerroteberes.authservice.domain.dto.response.ReadUserDTO;
import org.cerroteberes.authservice.domain.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
@AllArgsConstructor
public class AdapterOutUserRepository implements OutUserRepository {
    @Override
    public Optional<User> findById(Long userId) {
        return Optional.empty();
    }

    @Override
    public Optional<ReadUserDTO> findByIdRead(Long id) {
        return Optional.empty();
    }

    @Override
    public List<ReadUserDTO> findALlRead() {
        return List.of();
    }

    @Override
    public User save(User entity) {
        return null;
    }

    @Override
    public void delete(Long id) {
    }
}
