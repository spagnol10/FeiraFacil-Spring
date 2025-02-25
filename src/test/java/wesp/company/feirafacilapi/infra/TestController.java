package wesp.company.feirafacilapi.infra;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import wesp.company.feirafacilapi.exception.ApiException;

@RestController
public class TestController {

    @GetMapping("/test/api-exception")
    public void throwApiException() {
        throw new ApiException(404, "Resource not found", "Details about the API exception");
    }

    @GetMapping("/test/generic-exception")
    public void throwGenericException() {
        throw new RuntimeException("Unexpected server error");
    }

    @PostMapping("/test/validation-error")
    public void throwValidationError(@RequestBody @Valid TestRequest request) {
        // Simula uma validação
    }

    public static class TestRequest {
        @NotBlank(message = "Field cannot be blank")
        private String requiredField;

        public String getRequiredField() {
            return requiredField;
        }

        public void setRequiredField(String requiredField) {
            this.requiredField = requiredField;
        }
    }
}