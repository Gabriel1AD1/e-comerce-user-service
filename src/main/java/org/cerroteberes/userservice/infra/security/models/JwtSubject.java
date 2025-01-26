package org.cerroteberes.userservice.infra.security.models;

import lombok.Getter;

@Getter
public enum JwtSubject {
    MICROSERVICE("MICROSERVICE"),
    USER("USER");

    private final String value;

    JwtSubject(String value) {
        this.value = value;
    }

    public static JwtSubject fromString(String value) {
        for (JwtSubject subject : JwtSubject.values()) {
            if (subject.getValue().equalsIgnoreCase(value)) {
                return subject;
            }
        }
        throw new IllegalArgumentException("Invalid subject: " + value);
    }
}
