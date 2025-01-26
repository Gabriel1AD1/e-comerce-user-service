package org.cerroteberes.userservice.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.cerroteberes.userservice.domain.entity.enums.NameRole;
import org.cerroteberes.userservice.domain.entity.enums.RoleMicroservice;
import org.cerroteberes.userservice.domain.model.MCSVPrincipal;
import org.cerroteberes.userservice.domain.model.UserPrincipal;
import org.cerroteberes.userservice.infra.exeception.UnauthorizedException;
import org.cerroteberes.userservice.infra.security.models.JwtSubject;
import org.cerroteberes.userservice.infra.security.models.MCSVPrincipalSecurity;
import org.cerroteberes.userservice.infra.security.models.UserPrincipalSecurity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.auth0.jwt.algorithms.Algorithm.HMAC256;

@Component
public class JwtTokenUtil {
    private static final Logger log = LoggerFactory.getLogger(JwtTokenUtil.class);

    @Value("${secret.token}")
    private String SECRET_KEY; // Clave secreta para firmar el JWT (al menos 256 bits)

    /**
     * Decodifica el token JWT y extrae los claims.
     */
    public DecodedJWT decodeToken(String token) {
        return JWT.require(HMAC256(SECRET_KEY))
                .build()
                .verify(token);
    }

    /**
     * Extrae los roles del token, ya sea para microservicio o usuario.
     */
    private <T extends Enum<T>> List<T> extractRolesFromToken(DecodedJWT decodedJWT, Class<T> roleClass) {
        return decodedJWT.getClaim("roles").asList(String.class).stream()
                .map(role -> Enum.valueOf(roleClass, role)) // Aquí pasamos la clase roleClass
                .collect(Collectors.toList());
    }


    /**
     * Crea un UserPrincipal o MCSVPrincipal a partir de los claims del token JWT.
     */
    public Object getUserPrincipalFromToken(String token) {
        DecodedJWT decodedJWT = decodeToken(token);
        String subject = decodedJWT.getSubject();

        JwtSubject jwtSubject = JwtSubject.fromString(subject);

        return switch (jwtSubject) {
            case MICROSERVICE -> createMicroservicePrincipal(decodedJWT);
            case USER -> createUserPrincipal(decodedJWT);
            default -> throw new UnauthorizedException("Subject no contemplado");
        };
    }

    /**
     * Crea un principal para microservicio a partir del token.
     */
    private MCSVPrincipal createMicroservicePrincipal(DecodedJWT decodedJWT) {
        log.info("Autenticación por microservicio");

        String nameMicroservice = decodedJWT.getClaim("name_microservice").asString();
        List<RoleMicroservice> roles = extractRolesFromToken(decodedJWT, RoleMicroservice.class);

        log.info("Roles del microservicio {}", roles);

        return MCSVPrincipalSecurity.builder()
                .name(nameMicroservice)
                .roleMicroservices(roles)
                .build();
    }

    /**
     * Crea un principal para usuario a partir del token.
     */
    private UserPrincipal createUserPrincipal(DecodedJWT decodedJWT) {
        log.info("Autenticación por usuario");

        Long userId = decodedJWT.getClaim("user_id").asLong();
        List<NameRole> roles = extractRolesFromToken(decodedJWT, NameRole.class);

        return UserPrincipalSecurity.builder()
                .USERID(userId)
                .roles(roles)
                .build();
    }
}
