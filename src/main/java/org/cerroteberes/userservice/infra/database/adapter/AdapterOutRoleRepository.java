package org.cerroteberes.userservice.infra.database.adapter;

import lombok.AllArgsConstructor;
import org.cerroteberes.userservice.app.port.output.repo.OutRoleRepository;
import org.cerroteberes.userservice.domain.dto.response.ReadRoleDTO;
import org.cerroteberes.userservice.domain.entity.Role;
import org.cerroteberes.userservice.infra.database.mapper.RoleEntityMapper;
import org.cerroteberes.userservice.infra.database.repo.RoleEntityRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.cerroteberes.userservice.app.utils.OptionalMapper.wrapInOptional;

@Repository
@AllArgsConstructor
public class AdapterOutRoleRepository implements OutRoleRepository {
    private final RoleEntityRepository roleEntityRepository;
    private final RoleEntityMapper roleEntityMapper;

    @Override
    public List<Role> findAll() {
        return roleEntityMapper.toDomainList(
                roleEntityRepository.findAll()
        );
    }

    @Override
    public Optional<ReadRoleDTO> findByIdRead(Long id) {
        var roleInfo = roleEntityRepository.readFindById(id);
        return roleInfo.flatMap(roleEntityInfo -> wrapInOptional(ReadRoleDTO.builder()
                .nameRole(roleEntityInfo.getNameRole())
                .descriptionRole(roleEntityInfo.getDescriptionRole())
                .id(roleEntityInfo.getId())
                .build()));
    }

    @Override
    public List<ReadRoleDTO> findALlRead() {
        return roleEntityRepository.readFindAll().stream()
                .map(roleEntityInfo -> ReadRoleDTO.builder()
                        .id(roleEntityInfo.getId())
                        .nameRole(roleEntityInfo.getNameRole())
                        .descriptionRole(roleEntityInfo.getDescriptionRole())
                        .build())
                .collect(Collectors.toList());
    }


    @Override
    public Optional<Role> findById(Long id) {
        var role = roleEntityMapper.toDomain(roleEntityRepository.findById(id).orElse(null));
        return wrapInOptional(role);
    }

    @Override
    public Role save(Role entity) {
        return roleEntityMapper.toDomain(
                roleEntityRepository.save(roleEntityMapper.toEntity(entity))
        );
    }

    @Override
    public void delete(Long id) {
        roleEntityRepository.deleteById(id);
    }
}
