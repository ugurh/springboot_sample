package io.hrnugr.sample.dto.response;

import java.time.LocalDateTime;

public class ApiResponseDto {

    private final boolean success;
    private final String message;

    public ApiResponseDto(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getTimestamp() {
        return LocalDateTime.now().toString();
    }
}
