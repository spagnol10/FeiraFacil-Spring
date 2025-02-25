package wesp.company.feirafacilapi.exception;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ApiErrorResponse {
    private final int statusCode;
    private final String message;
    private final String error;
    private final LocalDateTime timestamp;

    public ApiErrorResponse(int statusCode, String message, String error) {
        this.statusCode = statusCode;
        this.message = message;
        this.error = error;
        this.timestamp = LocalDateTime.now();
    }
}