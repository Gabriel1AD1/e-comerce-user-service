package org.cerroteberes.userservice.app.implementation.service.role;

import lombok.AllArgsConstructor;
import org.cerroteberes.userservice.app.exception.EntityNotFoundException;
import org.cerroteberes.userservice.app.port.output.annotation.AppService;
import org.cerroteberes.userservice.domain.dto.response.ReadRoleDTO;
import org.cerroteberes.userservice.domain.entity.Role;
import org.cerroteberes.userservice.domain.entity.enums.NameRole;
import org.cerroteberes.userservice.domain.repo.RoleRepository;

import java.util.List;

@AppService
@AllArgsConstructor
public class RoleServiceI implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role create(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role update(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void delete(Long id) {
        roleRepository.delete(id);
    }

    @Override
    public List<ReadRoleDTO> FindALlRead() {
        return roleRepository.findALlRead();
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findByRoleName(NameRole nameRole) {
        return roleRepository.findByRoleName(nameRole).orElseThrow(
                () -> new EntityNotFoundException("No existe el role que se esta intentando buscar")
        );
    }
}
