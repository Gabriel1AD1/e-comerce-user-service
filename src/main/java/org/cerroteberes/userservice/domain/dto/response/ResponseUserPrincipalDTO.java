package org.cerroteberes.userservice.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.cerroteberes.userservice.domain.entity.enums.NameRole;

import java.util.List;
@Data
@AllArgsConstructor
@Builder
public class ResponseUserPrincipalDTO {
    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("password_encoded")
    private String passwordEncoded;

    @JsonProperty("roles")
    private List<NameRole> roles;
}
