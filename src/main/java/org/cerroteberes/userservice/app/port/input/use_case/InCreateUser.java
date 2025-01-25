package org.cerroteberes.authservice.app.port.input.use_case;

import jakarta.validation.Valid;
import org.cerroteberes.authservice.domain.dto.request.RequestCreateUserDTO;

public interface InCreateUser {
    Long execute(RequestCreateUserDTO dto);
}
