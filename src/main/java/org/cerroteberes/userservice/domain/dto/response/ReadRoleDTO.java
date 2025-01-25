package org.cerroteberes.authservice.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cerroteberes.authservice.domain.entity.enums.NameRole;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReadRoleDTO {

    @JsonProperty("role_id")
    private Long id;

    @JsonProperty("role_name")
    private NameRole nameRole;

    @JsonProperty("role_description")
    private String descriptionRole;
}
