package org.cerroteberes.userservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cerroteberes.userservice.domain.entity.enums.RoleMicroservice;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MCSVPrincipal {
    private String name;
    private List<RoleMicroservice> roleMicroservices;
}
