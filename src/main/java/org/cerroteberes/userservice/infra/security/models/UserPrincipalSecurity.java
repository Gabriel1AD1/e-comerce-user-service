package org.cerroteberes.userservice.infra.security.models;

import lombok.Builder;
import org.cerroteberes.userservice.domain.model.UserPrincipal;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class UserPrincipalSecurity extends UserPrincipal implements UserDetails {
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Convertir los roles de RoleMicroservice a SimpleGrantedAuthority
        return this.getRoles().stream()
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
