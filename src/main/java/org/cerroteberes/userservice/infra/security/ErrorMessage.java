package org.cerroteberes.userservice.infra.security;

import lombok.Getter;

@Getter
public enum ErrorMessage {

    TOKEN_INVALID("10000",
            "Token inválido o no presente",
            "Revisa el token que se envió correctamente"),
    USER_NOT_FOUND("10001",
            "El usuario no fue encontrado",
            "Asegúrate de que el ID del usuario es correcto"),
    ACCESS_DENIED("10002",
            "Acceso denegado",
            "No tienes los permisos necesarios para acceder a este recurso");

    private final String code;
    private final String message;
    private final String detail;

    ErrorMessage(String code, String message, String detail) {
        this.code = code;
        this.message = message;
        this.detail = detail;
    }

}
