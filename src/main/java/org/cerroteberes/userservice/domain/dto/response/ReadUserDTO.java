package org.cerroteberes.authservice.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReadUserDTO {

    @JsonProperty("user_id")
    private Long id;

    @JsonProperty("user_name")
    private String name;

    @JsonProperty("user_password")
    private String password;
}
