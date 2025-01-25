package org.cerroteberes.authservice.app.implementation.service.role;

import lombok.AllArgsConstructor;
import org.cerroteberes.authservice.app.port.output.annotation.AppService;
import org.cerroteberes.authservice.domain.dto.response.ReadRoleDTO;
import org.cerroteberes.authservice.domain.entity.Role;
import org.cerroteberes.authservice.domain.repo.RoleRepository;

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
}
