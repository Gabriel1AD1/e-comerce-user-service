package org.cerroteberes.userservice.app.port.input.use_case;

import org.cerroteberes.userservice.domain.dto.request.RequestUpdateUserDTO;

public interface InUpdateUser {
    void execute(RequestUpdateUserDTO dto, Long id);
}
