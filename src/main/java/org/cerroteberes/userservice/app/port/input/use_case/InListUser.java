package org.cerroteberes.userservice.app.port.input.use_case;

import org.cerroteberes.userservice.domain.dto.response.ReadUserDTO;

import java.util.List;

public interface InListUser {
    List<ReadUserDTO> executeListUser();
}
