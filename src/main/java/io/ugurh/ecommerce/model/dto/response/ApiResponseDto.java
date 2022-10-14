package io.ugurh.ecommerce.model.dto.response;

import lombok.Getter;

import java.time.LocalDateTime;

/**
 * @author harun ugur
 */
@Getter
public class ApiResponseDto {

    private final boolean success;
    private final String message;
    private final LocalDateTime dateTime;

    public ApiResponseDto(boolean success, String message) {
        this.success = success;
        this.message = message;
        this.dateTime = LocalDateTime.now();
    }

}
