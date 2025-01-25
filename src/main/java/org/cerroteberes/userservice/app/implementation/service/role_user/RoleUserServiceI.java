package org.cerroteberes.authservice.app.implementation.service.role_user;

import lombok.AllArgsConstructor;
import org.cerroteberes.authservice.app.port.output.annotation.AppService;
import org.cerroteberes.authservice.domain.entity.RoleUser;
import org.cerroteberes.authservice.domain.repo.RoleUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@AppService
@AllArgsConstructor
public class RoleUserServiceI implements RoleUserService {
    private static final Logger log = LoggerFactory.getLogger(RoleUserServiceI.class);
    private final RoleUserRepository roleUserRepository;
    @Override
    public RoleUser create(Long idUser, Long roleId) {
        return roleUserRepository.save(RoleUser.builder()
                        .idRole(roleId)
                        .idUser(idUser)
                .build());
    }

    @Override
    public RoleUser update(Long idUser, Long roleId) {
        RoleUser roleUser = roleUserRepository.findByIdUserAndIdRole(idUser,roleId);
        roleUser.setIdUser(idUser);
        roleUser.setIdRole(roleId);
        return roleUserRepository.save(roleUser);
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
}
