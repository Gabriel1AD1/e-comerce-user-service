package org.cerroteberes.userservice.domain.repo;

import org.cerroteberes.userservice.domain.comons.ReadRepository;
import org.cerroteberes.userservice.domain.comons.WriteRepository;
import org.cerroteberes.userservice.domain.entity.UserRole;
import org.cerroteberes.userservice.domain.entity.enums.NameRole;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio especializado para la gestión de relaciones entre usuarios y roles.
 * <p>
 * Esta interfaz extiende {@link WriteRepository} para manejar operaciones de escritura
 * relacionadas con la entidad {@link UserRole} y define métodos específicos para trabajar
 * con la asociación entre usuarios y roles.
 * </p>
 *
 * @see WriteRepository
 */
public interface RoleUserRepository extends WriteRepository<UserRole, Long> {

    /**
     * Busca una relación específica entre un usuario y un rol.
     * <p>
     * Este método permite recuperar una instancia de {@link UserRole} basada en el
     * identificador del usuario y el identificador del rol.
     * </p>
     *
     * @param idUser El identificador único del usuario.
     * @param roleId El identificador único del rol.
     * @return Un {@link Optional} que contiene la relación usuario-rol si se encuentra, o vacío si no existe.
     */
    Optional<UserRole> findByIdUserAndIdRole(Long idUser, Long roleId);

    /**
     * Elimina una relación específica entre un usuario y un rol.
     * <p>
     * Este método elimina una entrada de la tabla de relaciones usuario-rol basada en
     * el identificador del usuario y el rol.
     * </p>
     *
     * @param idUser El identificador único del usuario.
     * @param roleId El identificador único del rol.
     * @return Un entero que indica el número de filas afectadas por la operación (1 si se eliminó una relación, 0 en caso contrario).
     */
    int deleteByIdUserAndIdRole(Long idUser, Long roleId);

    List<NameRole> findAllByUserId(Long userId);
}
