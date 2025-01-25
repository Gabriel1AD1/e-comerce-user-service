package org.cerroteberes.authservice.domain.entity;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cerroteberes.authservice.domain.entity.enums.NameRole;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @NotNull
    private Long id;
    @NotNull
    private NameRole nameRole;
    @NotNull
    private String descriptionRole;
    @NotNull
    private Instant createAt;
    @NotNull
    private Instant updateAt;
}
