package org.cerroteberes.userservice.app.port.input.use_case;

import jakarta.validation.constraints.NotNull;
import org.cerroteberes.userservice.domain.dto.response.ResponseUserPrincipalDTO;

public interface InGetUserPrincipalForEmail {
    ResponseUserPrincipalDTO executeGetUserPrincipalForEmail(@NotNull String email);
}
