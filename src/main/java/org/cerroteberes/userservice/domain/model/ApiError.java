package org.cerroteberes.userservice.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
public class ApiError {

    @JsonProperty("status")
    private HttpStatus status;
    @JsonProperty("timestamp")
    private LocalDateTime timestamp;
    @JsonProperty("message")
    private String message;

    // Excluir el campo si es null o vacío
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("debug_message")
    private String debugMessage;

    // Excluir el campo si es null o vacío
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("errors")
    private Map<String, Object> errors;

    // Constructor personalizado
    public ApiError(HttpStatus status, String message, String debugMessage, Map<String, Object> errors) {
        this.status = status;
        this.timestamp = LocalDateTime.now(); // Asignamos el tiempo actual automáticamente
        this.message = message;
        this.debugMessage = debugMessage;
        this.errors = errors;
    }
}
