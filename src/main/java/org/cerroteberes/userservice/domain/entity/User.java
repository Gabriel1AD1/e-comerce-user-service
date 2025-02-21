package org.cerroteberes.userservice.domain.entity;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.cerroteberes.userservice.domain.entity.enums.NameRole;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class User {
    @NotNull
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private Instant createAt;
    @NotNull
    private Instant updateAt;
}
