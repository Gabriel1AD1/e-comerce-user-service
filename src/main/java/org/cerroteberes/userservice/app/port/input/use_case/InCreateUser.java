package org.cerroteberes.userservice.app.port.input.use_case;

import org.cerroteberes.userservice.domain.dto.request.RequestCreateUserDTO;
import org.cerroteberes.userservice.domain.entity.User;
import org.cerroteberes.userservice.domain.entity.enums.TypeUserSignup;

public interface InCreateUser {
    User executeCreateUser(RequestCreateUserDTO dto, TypeUserSignup typeUserSignup);
}
