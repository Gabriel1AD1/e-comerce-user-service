package org.cerroteberes.userservice.infra.security;

public class EndpointSecurityConstant {
    public static final String[] ENDPOINT_PUBLIC = {

    };

    public static final String[] ENDPOINT_PRIVATE = {

    };
    public static final String[] ENDPOINT_SWAGGER= {
            "/swagger-ui.html",
            "/v3/api-docs/**",
            "/swagger-ui/**"
    };
}

