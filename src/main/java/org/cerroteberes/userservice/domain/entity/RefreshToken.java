package org.cerroteberes.authservice.domain.entity;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data@Builder
@AllArgsConstructor
public class RefreshToken {
    @NotNull
    private Long id;
    @NotNull
    private Long idUser;
    @NotNull
    private String token;
}
