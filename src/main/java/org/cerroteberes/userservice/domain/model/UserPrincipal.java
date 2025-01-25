package org.cerroteberes.userservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cerroteberes.userservice.domain.entity.enums.NameRole;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPrincipal {
    private Long USERID;
    private List<NameRole> roles;
}
