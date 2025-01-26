package org.cerroteberes.userservice.infra.security.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.cerroteberes.userservice.domain.entity.enums.RoleMicroservice;
import org.cerroteberes.userservice.domain.model.MCSVPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;
public class MCSVPrincipalSecurity extends MCSVPrincipal implements UserDetails {
    private static final Logger log = LoggerFactory.getLogger(MCSVPrincipalSecurity.class);

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        log.info("Roles: {}", this.getRoleMicroservices());
        return this.getRoleMicroservices().stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toList());
    }


    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }
}
