package com.labotec.traccar.infra.security;

public class EndpointSecurityConstant {
    public static final String[] ENDPOINT_PUBLIC = {
            "/api/v1/device/last/position/imei/**",
            "/api/v1/device/last/**",
            "/api/v1/integration-traccar/**",
            "/ws",
            "/api/v1/public/images/vehicles",
            "/api/v1/public/images/locals",
            "/api/v1/device/last/position/imei/**",
            "/api/v1/public" ,
            "/api/v1/integration-traccar"};

    public static final String[] ENDPOINT_PRIVATE = {
            "/api/v1/public",
            "/admin/**",
            "/api/**"
    };
    public static final String[] ENDPOINT_SWAGGER= {
            "/swagger-ui.html",
            "/v3/api-docs/**",
            "/swagger-ui/**"
    };
}

