package org.cerroteberes.authservice.domain.repo;

import org.cerroteberes.authservice.domain.comons.ReadRepository;
import org.cerroteberes.authservice.domain.comons.WriteRepository;
import org.cerroteberes.authservice.domain.dto.response.ReadUserDTO;
import org.cerroteberes.authservice.domain.entity.User;

import java.util.Optional;

public interface UserRepository extends WriteRepository<User,Long>, ReadRepository<ReadUserDTO,Long> {
    Optional<User> findById(Long userId);
}
