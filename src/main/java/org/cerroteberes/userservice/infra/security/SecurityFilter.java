
package org.cerroteberes.userservice.infra.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.cerroteberes.userservice.domain.model.ApiError;
import org.cerroteberes.userservice.domain.model.MCSVPrincipal;
import org.cerroteberes.userservice.domain.model.UserPrincipal;
import org.cerroteberes.userservice.infra.exeception.UnauthorizedException;
import org.cerroteberes.userservice.infra.security.models.MCSVPrincipalSecurity;
import org.cerroteberes.userservice.infra.security.models.UserPrincipalSecurity;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.cerroteberes.userservice.infra.security.ErrorMessage.TOKEN_INVALID;

@Component
@AllArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {
    private final ConfigurationSecurity configurationSecurity;
    private final ObjectMapper objectMapper;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        switch (configurationSecurity.getAuthenticationType()) {
            case NONE, BASIC:
                break;
            case BEARER:
                doBearerFilter(request, response, filterChain);
                break;
            default:
                throw new UnauthorizedException("Authentication type not supported");
        }


    }

    private void doBearerFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Optional<String> authorizationHeader = Optional.ofNullable(request.getHeader("Authorization"));

        logger.info("Authorization : " + authorizationHeader);

        if (authorizationHeader.isPresent() && authorizationHeader.get().startsWith("Bearer ")) {
            String bearerToken = authorizationHeader.get().substring(7);  // Extrae el token

            try {
                // Obtener Principal desde el token
                Object userPrincipalObj = jwtTokenUtil.getUserPrincipalFromToken(bearerToken);

                // Verificar si es un MCSVPrincipal
                if (userPrincipalObj instanceof MCSVPrincipal mcsvPrincipal) {
                    // Crear una autenticación basada en el UserPrincipal

                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(mcsvPrincipal, null, mcsvPrincipal.getRoleMicroservices().stream()
                                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                                    .collect(Collectors.toList()));
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    // Establecer el contexto de seguridad
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    logger.debug("AUTENTICACION EXITOSA PARA MICROSERVICIO");
                }

                // Verificar si es un UserPrincipal
                if (userPrincipalObj instanceof UserPrincipal userPrincipal) {
                    // Crear una autenticación basada en el UserPrincipal
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getRoles().stream()
                                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                                    .collect(Collectors.toList()));
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    // Establecer el contexto de seguridad
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    logger.debug("AUTENTICACION EXITOSA PARA USUARIO");
                }


            } catch (Exception e) {
                logger.error("Error al procesar el token JWT: " + e.getMessage());
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                logger.warn("Error en el procesamiento del jwt {}" + e.getMessage());
                response.getWriter().write("Token inválido");
                return;
            }

        } else {
            ApiError apiError = ApiError.unauthorizedForToken();
            // Establecer la respuesta en formato JSON
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write(objectMapper.writeValueAsString(apiError));
            logger.debug("La autenticación ha sido fallida");
            return;
        }

        // Continuar con la siguiente cadena de filtros
        filterChain.doFilter(request, response);
    }


}
