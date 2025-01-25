package com.labotec.traccar.infra.security;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class ConfigurationSecurity {
    @Value("${security.authentication-type}")
    private AuthenticationType authenticationType;

    @Value("${security.user-id-admin}")
    private Long userIdAdmin;

}
