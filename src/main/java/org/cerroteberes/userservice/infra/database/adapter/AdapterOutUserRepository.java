package org.cerroteberes.userservice.infra.database.adapter;

import lombok.AllArgsConstructor;
import org.cerroteberes.userservice.app.port.output.repo.OutUserRepository;
import org.cerroteberes.userservice.domain.dto.response.ReadUserDTO;
import org.cerroteberes.userservice.domain.entity.User;
import org.cerroteberes.userservice.infra.database.entity.UserEntity;
import org.cerroteberes.userservice.infra.database.mapper.UserEntityMapper;
import org.cerroteberes.userservice.infra.database.repo.UserEntityRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.cerroteberes.userservice.app.utils.OptionalMapper.wrapInOptional;

@Repository
@AllArgsConstructor
public class AdapterOutUserRepository implements OutUserRepository {
    private final UserEntityRepository userEntityRepository;
    private final UserEntityMapper userEntityMapper;
    @Override
    public Optional<User> findById(Long userId) {
        UserEntity userEntity = userEntityRepository.findById(userId).orElse(null);
        User user = userEntityMapper.toDomain(userEntity);
        return wrapInOptional(user);
    }

    @Override
    public Optional<ReadUserDTO> findByIdRead(Long id) {
        var userEntityInfo = userEntityRepository.readFindById(id);
        return userEntityInfo.flatMap(entityInfo -> wrapInOptional(ReadUserDTO.builder()
                .name(entityInfo.getName())
                .id(entityInfo.getId())
                .build()));
    }

    @Override
    public List<ReadUserDTO> findALlRead() {
        return userEntityRepository.readFindAll().stream()
                .map(userEntityInfo -> ReadUserDTO.builder()
                        .id(userEntityInfo.getId())
                        .name(userEntityInfo.getName()).build())
                .collect(Collectors.toList());
    }

    @Override
    public User save(User entity) {
        return userEntityMapper.toDomain(
                userEntityRepository.save(
                        userEntityMapper.toEntity(entity)
                )
        );
    }

    @Override
    public void delete(Long id) {
        userEntityRepository.deleteById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        UserEntity userEntity = userEntityRepository.findByEmail(email);
        User user = userEntityMapper.toDomain(userEntity);
        return wrapInOptional(user);
    }
}
