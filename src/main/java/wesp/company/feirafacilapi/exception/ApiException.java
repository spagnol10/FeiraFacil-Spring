package wesp.company.feirafacilapi.exception;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {
    private final int statusCode;
    private final String error;

    public ApiException(int statusCode, String message, String error) {
        super(message);
        this.statusCode = statusCode;
        this.error = error;
    }

}