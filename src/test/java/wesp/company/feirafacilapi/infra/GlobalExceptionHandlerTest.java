package wesp.company.feirafacilapi.infra;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import wesp.company.feirafacilapi.exception.GlobalExceptionHandler;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class GlobalExceptionHandlerTest {

    private final MockMvc mockMvc;

    @Autowired
    public GlobalExceptionHandlerTest(GlobalExceptionHandler exceptionHandler) {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new TestController())
                .setControllerAdvice(exceptionHandler)
                .build();
    }

    @Test
    void whenApiException_thenHandleApiException() throws Exception {
        mockMvc.perform(get("/test/api-exception"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.NOT_FOUND.value()))
                .andExpect(jsonPath("$.message").value("Resource not found"))
                .andExpect(jsonPath("$.error").value("Details about the API exception"));
    }

    @Test
    void whenGenericException_thenHandleGenericException() throws Exception {
        mockMvc.perform(get("/test/generic-exception"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .andExpect(jsonPath("$.message").value("An unexpected error occurred"))
                .andExpect(jsonPath("$.error").isNotEmpty());
    }

    @Test
    void whenValidationFails_thenHandleValidationException() throws Exception {
        mockMvc.perform(post("/test/validation-error")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"invalidField\": \"value\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.message").value("Validation failed"))
                .andExpect(jsonPath("$.error").isNotEmpty());
    }
}