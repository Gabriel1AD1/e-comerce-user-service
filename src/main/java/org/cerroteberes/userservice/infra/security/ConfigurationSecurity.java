package org.cerroteberes.userservice.infra.security;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class ConfigurationSecurity {
    @Value("${security.authentication-type}")
    private AuthenticationType authenticationType;

}
