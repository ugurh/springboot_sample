package io.hrnugr.ecommerce.handler.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author harun ugur
 * @project sample
 * @created 13.10.2022 - 21:52
 */
@Data
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(value = {"stackTrace", "cause", "localizedMessage", "suppressed"})
public class ApiError extends Exception {

    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime timestamp;
    private String message;
    private List<ApiSubError> errors;

    private ApiError() {
        timestamp = LocalDateTime.now();
    }

    public ApiError(HttpStatus status) {
        this();
        this.status = status;
    }

    public ApiError(HttpStatus status, Throwable ex) {
        this();
        this.status = status;
        this.message = "Unexpected error";
    }

    public ApiError(HttpStatus status, String message) {
        this();
        this.status = status;
        this.message = message;
    }

    public ApiError(String message, Throwable ex) {
        super(message, ex);
    }

    public ApiError(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.message = message;
    }

}
