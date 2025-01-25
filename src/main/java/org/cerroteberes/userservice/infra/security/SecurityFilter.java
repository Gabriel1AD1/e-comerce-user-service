/**
 * SecurityFilter.java
 * <p>
 * Este filtro de seguridad personalizado extiende OncePerRequestFilter de Spring Security
 * para manejar diferentes tipos de autenticación configurados dinámicamente. Su objetivo
 * es proteger las solicitudes entrantes y establecer el contexto de seguridad según el
 * tipo de autenticación definido.
 * <p>
 * Características principales:
 * - Soporta múltiples tipos de autenticación: NONE, BASIC, BEARER, USER_ID.
 * - Establece el contexto de seguridad para cada solicitud autenticada.
 * - Retorna errores detallados en formato JSON para solicitudes no autorizadas.
 * - Simplifica la migración futura hacia autenticación basada en tokens (por ejemplo, JWT).
 * - Preparado para integrarse con Keycloak para una gestión centralizada de usuarios y roles.
 * <p>
 * Nota importante:
 * Actualmente, el sistema utiliza IDs de usuario enviados en los headers para la autenticación y tambien
 * Se enviá los headers de authentication cambiar eso.
 * Esto se migrará a un enfoque basado en tokens (por ejemplo, JWT) para mejorar la seguridad y escalabilidad.
 * La implementación también está preparada para integrar Keycloak, lo que permitirá gestionar
 * usuarios, roles y autenticación desde una fuente centralizada, mejorando la flexibilidad y el mantenimiento.
 */

package com.labotec.traccar.infra.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.labotec.traccar.infra.common.ApiError;
import com.labotec.traccar.infra.web.rest.traccar.exception.UnauthorizedException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@AllArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {
    private final ConfigurationSecurity configurationSecurity;
    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException
    {

        switch (configurationSecurity.getAuthenticationType()) {
            case NONE:
                break;
            case BASIC:
                doBasicFilter(request, response, filterChain);
                break;
            case BEARER:
                doBearerFilter(request, response, filterChain);
                break;
            case USER_ID:
                doUserIdFilter(request, response, filterChain);
                break;
            default:
                throw new UnauthorizedException("Authentication type not supported");
        }


    }
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return path.startsWith("/api/v1/device/last/position/imei/");
    }

    private void doUserIdFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Optional<String> userIdHeader = Optional.ofNullable(request.getHeader("userId"));

        logger.info("Authorization : " + userIdHeader);

        if (userIdHeader.isPresent()) {

            Long userId = Long.parseLong(userIdHeader.get());

            // Obtener roles (si es que el token los contiene, o asignarlos de alguna forma)
            Collection<? extends GrantedAuthority> authorities = getRolesFromToken(userId.toString());  // Método para obtener roles del token

            // Crear un UserPrincipal con el userId y los roles obtenidos
            UserPrincipal userPrincipal = getUserPrincipal(userId, authorities);
            // Crear una autenticación
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userPrincipal, null, userPrincipal.getAuthorities());

            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }else {
            ApiError apiError = ApiError.builder()
                    .status(HttpStatus.UNAUTHORIZED)
                    .debugMessage("Review the header and check if it exists or if it is being sent correctly.")
                    .timestamp(LocalDateTime.now())
                    .message("Unauthorized: User ID missing or invalid.")
                    .build();

            // Establecer la respuesta en formato JSON
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write(objectMapper.writeValueAsString(apiError));
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        // Continuar con la siguiente cadena de filtros
        filterChain.doFilter(request, response);
    }

    private void doBasicFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
    }

    private void doBearerFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Optional<String> authorizationHeader = Optional.ofNullable(request.getHeader("Authorization"));

        logger.info("Authorization : " + authorizationHeader);

        if (authorizationHeader.isPresent() && authorizationHeader.get().startsWith("Bearer ")) {
            String bearerToken = authorizationHeader.get().substring(7);  // Esto extrae el token

            logger.debug("Bearer Token : " + bearerToken);

            // Aquí se puede decodificar el token y obtener el userId, roles, etc.
            Long userId = getUserIdFromToken(bearerToken);  // Imagina que tienes un método para obtener el userId del token
            logger.debug("userId request : " + userId);
            // Obtener roles (si es que el token los contiene, o asignarlos de alguna forma)
            Collection<? extends GrantedAuthority> authorities = getRolesFromToken(String.valueOf(userId));  // Método para obtener roles del token
            logger.debug("authorities request : " + authorities);
            logger.debug("userId admin : " + configurationSecurity.getUserIdAdmin());
            // Crear un UserPrincipal con el userId y los roles obtenidos
            UserPrincipal userPrincipal = getUserPrincipal(userId,authorities);
            // Crear una autenticación
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userPrincipal, null, userPrincipal.getAuthorities());

            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            logger.info("La autenticacion fue un exito para el usuario principal  : "+userPrincipal.toString());
        }else {
            ApiError apiError = ApiError.builder()
                    .status(HttpStatus.UNAUTHORIZED)
                    .timestamp(LocalDateTime.now())
                    .debugMessage("Review the header and check if it exists or if it is being sent correctly.")
                    .message("Unauthorized: User ID missing or invalid.")
                    .build();

            // Establecer la respuesta en formato JSON
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write(objectMapper.writeValueAsString(apiError));
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            logger.warn("La autenticaccion ha sido fallida ");
            return;
        }

        // Continuar con la siguiente cadena de filtros
        filterChain.doFilter(request, response);
        return;
    }

    private Long getUserIdFromToken(String bearerToken) {
        try {
            return Long.parseLong(bearerToken);

        }catch (Exception e){
            throw new UnauthorizedException("XD");
        }
    }
    private UserPrincipal getUserPrincipal(Long userId,Collection<? extends GrantedAuthority> authorities) {
        return new UserPrincipal(userId, authorities);
    }
    private Collection<? extends GrantedAuthority> getRolesFromToken(String token) {
        if (Objects.equals(Long.parseLong(token), configurationSecurity.getUserIdAdmin())){
            return List.of(new SimpleGrantedAuthority(RolUser.ROLE_ADMIN.name()));
        }else {
            return List.of(new SimpleGrantedAuthority(RolUser.ROLE_USER.name()));}
    }
}
