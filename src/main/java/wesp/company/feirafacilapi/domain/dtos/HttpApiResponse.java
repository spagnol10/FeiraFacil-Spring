package wesp.company.feirafacilapi.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HttpApiResponse<T> {
    private int statusCode;
    private String message;
    private T data;
    private LocalDateTime timestamp;

    public HttpApiResponse(int statusCode, String message, T data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }
}