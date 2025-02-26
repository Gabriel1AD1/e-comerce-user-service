package org.cerroteberes.userservice.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

import static org.cerroteberes.userservice.infra.security.ErrorMessage.TOKEN_INVALID;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
public class ApiError {

    @JsonProperty("status")
    private HttpStatus status;
    @JsonProperty("code_status")
    private Long codeStatus;
    @JsonProperty("timestamp")
    private LocalDateTime timestamp;
    @JsonProperty("message")
    private String message;
    @JsonProperty("code_status_error")
    private String codeStatusError;
    // Excluir el campo si es null o vacío
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("errors")
    private List<String> errors;

    // Excluir el campo si es null o vacío
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("debug_message")
    private String debugMessage;

    // Factory Methods para crear instancias de ApiError
    public static ApiError notFound(String message) {
        return ApiError.builder()
                .codeStatus(404L)
                .status(HttpStatus.NOT_FOUND)
                .timestamp(LocalDateTime.now())
                .message(message)
                .build();
    }

    public static ApiError badRequest(String message, String debugMessage) {
        return ApiError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .codeStatus(400L)
                .timestamp(LocalDateTime.now())
                .message(message)
                .debugMessage(debugMessage)
                .build();
    }
    public static ApiError unauthorized(String message, String details,String codeStatusError) {
        return ApiError.builder()
                .codeStatus(401L)
                .codeStatusError(codeStatusError)
                .status(HttpStatus.UNAUTHORIZED)
                .timestamp(LocalDateTime.now())
                .errors(List.of(details))
                .message(message)
                .build();
    }
    public static ApiError internalServerError(String message, String debugMessage) {
        return ApiError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .codeStatus(500L)
                .timestamp(LocalDateTime.now())
                .message(message)
                .debugMessage(debugMessage)
                .build();
    }

    public static ApiError unauthorizedForToken() {
        return ApiError.unauthorized(TOKEN_INVALID.getMessage(),TOKEN_INVALID.getDetail(),TOKEN_INVALID.getCode());
    }
}
