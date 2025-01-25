package org.cerroteberes.userservice.infra.security;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.cerroteberes.userservice.domain.entity.enums.NameRole;
import org.cerroteberes.userservice.domain.model.UserPrincipal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.auth0.jwt.algorithms.Algorithm.HMAC256;

@Component
public class JwtTokenUtil {
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
     * Crea un UserPrincipal a partir de los claims del token JWT.
     */
    public UserPrincipal getUserPrincipalFromToken(String token) {
        DecodedJWT decodedJWT = decodeToken(token);

        // Extraer el userId y los roles del token
        Long userId = decodedJWT.getClaim("user_id").asLong();
        List<NameRole> roles = decodedJWT.getClaim("roles").asList(String.class).stream()
                .map(NameRole::valueOf) // Convierte los roles en Enum
                .collect(Collectors.toList());

        // Crear el UserPrincipal
        return UserPrincipal.builder()
                .USERID(userId)
                .roles(roles)
                .build();
    }
}
