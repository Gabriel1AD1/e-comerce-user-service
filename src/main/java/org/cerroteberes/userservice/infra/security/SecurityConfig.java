/**
 * SecurityConfig.java
 * Esta clase configura la seguridad de Spring Security para la aplicación,
 * definiendo la cadena de filtros de seguridad y las reglas de acceso
 * para diferentes endpoints. Personaliza el comportamiento para endpoints
 * públicos, privados y Swagger basándose en roles y autoridades.
 * Características:
 * - Deshabilita CORS y la protección contra CSRF para simplificar la configuración.
 * - Agrega un filtro de seguridad personalizado para la autenticación basada en tokens.
 * - Define el control de acceso a los endpoints con roles y autoridades.
 */

package com.labotec.traccar.infra.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.labotec.traccar.infra.security.EndpointSecurityConstant.*;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    /**
     * Filtro de seguridad personalizado para la autenticación basada en tokens.
     */
    private final SecurityFilter securityFilter;

    /**
     * Configura la cadena de filtros de seguridad para la aplicación.
     *
     * @param http Objeto HttpSecurity para personalizar las configuraciones de seguridad.
     * @return SecurityFilterChain la cadena de filtros de seguridad configurada.
     * @throws Exception si ocurre un error durante la configuración.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // Deshabilita CORS y CSRF
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                // Agrega un filtro de seguridad personalizado antes del UsernamePasswordAuthenticationFilter
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                // Configura las reglas de autorización
                .authorizeHttpRequests(authorize -> authorize
                        // Endpoints públicos: accesibles para todos sin autenticación
                        .requestMatchers(ENDPOINT_PUBLIC).permitAll()
                        // Endpoints privados: requieren la autoridad ROLE_USER
                        .requestMatchers(ENDPOINT_PRIVATE).hasAnyAuthority(RolUser.ROLE_USER.name(),RolUser.ROLE_ADMIN.name())
                        // Endpoints Swagger: requieren la autoridad ROLE_ADMIN
                        .requestMatchers(ENDPOINT_SWAGGER).hasAnyAuthority(RolUser.ROLE_ADMIN.name())
                        // Todas las demás solicitudes: requieren autenticación
                        .anyRequest().authenticated()
                )
                // Deshabilita la autenticación HTTP Basic
                .httpBasic(AbstractHttpConfigurer::disable)
                .build();
    }

}
