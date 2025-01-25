package com.labotec.traccar.infra.security;

public enum AuthenticationType {
    NONE,           // Sin autenticación.
    BASIC,          // Autenticación básica con usuario y contraseña.
    BEARER,         // Autenticación con token Bearer (por ejemplo, JWT).
    OAUTH2,         // Autenticación mediante OAuth2.
    SESSION,        // Autenticación basada en sesión.
    API_KEY,         // Autenticación con clave de API.
    USER_ID         // Autenticación con ID de usuario.
}
