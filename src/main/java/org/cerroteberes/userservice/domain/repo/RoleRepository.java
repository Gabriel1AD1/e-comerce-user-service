package org.cerroteberes.userservice.domain.repo;

import org.cerroteberes.userservice.domain.comons.ReadRepository;
import org.cerroteberes.userservice.domain.comons.WriteRepository;
import org.cerroteberes.userservice.domain.dto.response.ReadRoleDTO;
import org.cerroteberes.userservice.domain.entity.Role;

import java.util.List;

/**
 * Repositorio especializado para la gestión de roles.
 * <p>
 * Esta interfaz combina las capacidades de lectura y escritura, extendiendo las interfaces genéricas
 * {@link WriteRepository} y {@link ReadRepository}. Proporciona métodos para manejar entidades
 * de tipo {@link Role} y representaciones de solo lectura ({@link ReadRoleDTO}).
 * </p>
 *
 * @see WriteRepository
 * @see ReadRepository
 */
public interface RoleRepository extends WriteRepository<Role, Long>, ReadRepository<ReadRoleDTO, Role, Long> {

    /**
     * Recupera todos los roles almacenados.
     * <p>
     * Este método devuelve una lista completa de entidades {@link Role} presentes en el sistema.
     * </p>
     *
     * @return Una lista de todos los roles registrados en la base de datos.
     */
    List<Role> findAll();
}
