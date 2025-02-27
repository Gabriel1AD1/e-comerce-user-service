package org.cerroteberes.userservice.infra.seeder;

import lombok.AllArgsConstructor;
import org.cerroteberes.userservice.domain.entity.enums.NameRole;
import org.cerroteberes.userservice.infra.database.entity.RoleEntity;
import org.cerroteberes.userservice.infra.database.repo.RoleEntityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
@AllArgsConstructor
public class RoleInitializer implements CommandLineRunner {
    private final RoleEntityRepository roleEntityRepository;

    @Override
    public void run(String... args) throws Exception {

        if (roleEntityRepository.count() == 0) {
            RoleEntity admin = RoleEntity.builder()
                    .descriptionRole("Rol de administrador tiene poder y acceso a todo ")
                    .nameRole(NameRole.ADMIN)
                    .createAt(Instant.now())
                    .updateAt(Instant.now())
                    .build();

            RoleEntity vendor = RoleEntity.builder()
                    .descriptionRole("Rol de vendedor, tiene permisos limitados para gestionar productos y ventas.")
                    .nameRole(NameRole.VENDOR)
                    .createAt(Instant.now())
                    .updateAt(Instant.now())
                    .build();

            RoleEntity user = RoleEntity.builder()
                    .descriptionRole("Rol de usuario básico con acceso restringido.")
                    .nameRole(NameRole.USER)
                    .createAt(Instant.now())
                    .updateAt(Instant.now())
                    .build();

            RoleEntity client = RoleEntity.builder()
                    .descriptionRole("Rol de cliente, con acceso a la compra de productos.")
                    .nameRole(NameRole.CLIENT)
                    .createAt(Instant.now())
                    .updateAt(Instant.now())
                    .build();

            roleEntityRepository.saveAll(
                    List.of(admin, vendor, user, client)
            );
        }

    }
}
