package org.cerroteberes.userservice.app.implementation.service.role_user;

import lombok.AllArgsConstructor;
import org.cerroteberes.userservice.app.exception.EntityNotFoundException;
import org.cerroteberes.userservice.app.port.output.annotation.AppService;
import org.cerroteberes.userservice.domain.entity.UserRole;
import org.cerroteberes.userservice.domain.entity.enums.NameRole;
import org.cerroteberes.userservice.domain.repo.RoleUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@AppService
@AllArgsConstructor
public class RoleUserServiceI implements RoleUserService {
    private static final Logger log = LoggerFactory.getLogger(RoleUserServiceI.class);
    private final RoleUserRepository roleUserRepository;
    @Override
    public UserRole create(Long idUser, Long roleId) {
        return roleUserRepository.save(UserRole.builder()
                        .idRole(roleId)
                        .idUser(idUser)
                .build());
    }

    @Override
    public UserRole update(Long idUser, Long roleId) {
        UserRole userRole = roleUserRepository.findByIdUserAndIdRole(idUser,roleId).orElseThrow(
                ()-> new EntityNotFoundException("Usuario asociado a un rol no encontrado")
        );
        userRole.setIdUser(idUser);
        userRole.setIdRole(roleId);
        return roleUserRepository.save(userRole);
    }

    @Override
    public void delete(Long idUser, Long roleId) {
        int arrowAffected = roleUserRepository.deleteByIdUserAndIdRole(idUser,roleId);
        if (arrowAffected>0){
            log.debug("Se ha eliminado correctamente la asociacion entre un usuario y un rol ");
        }else {
            log.debug("Hubo un problema al eliminar el role");
        }
    }

    @Override
    public List<NameRole> getRoleByUserId(Long userId) {
        return roleUserRepository.findAllByUserId(userId);
    }
}
