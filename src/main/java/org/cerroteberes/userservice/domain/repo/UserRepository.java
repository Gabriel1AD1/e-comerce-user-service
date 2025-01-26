package org.cerroteberes.userservice.domain.repo;

import org.cerroteberes.userservice.domain.comons.ReadRepository;
import org.cerroteberes.userservice.domain.comons.WriteRepository;
import org.cerroteberes.userservice.domain.dto.response.ReadUserDTO;
import org.cerroteberes.userservice.domain.entity.User;

import java.util.Optional;


/**
 * Repositorio especializado para la gestión de usuarios.
 * <p>
 * Esta interfaz combina las capacidades de lectura y escritura, extendiendo las interfaces genéricas
 * {@link WriteRepository} y {@link ReadRepository}. Proporciona métodos para manejar entidades
 * de usuario ({@link User}) y sus representaciones de solo lectura ({@link ReadUserDTO}).
 * </p>
 *
 * @see WriteRepository
 * @see ReadRepository
 */
public interface UserRepository extends WriteRepository<User, Long>, ReadRepository<ReadUserDTO, User, Long> {

    Optional<User> findByEmail(String email);
}
