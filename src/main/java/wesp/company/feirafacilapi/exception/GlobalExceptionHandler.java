package wesp.company.feirafacilapi.exception;

import jakarta.persistence.EntityNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.nio.file.AccessDeniedException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private ResponseEntity<ApiErrorResponse> createErrorResponse(HttpStatus status, String message, String details) {
        ApiErrorResponse response = new ApiErrorResponse(
                status.value(),
                message,
                details
        );
        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiErrorResponse> handleApiException(ApiException ex) {
        logger.error("Api Exception: {}", ex.getMessage(), ex);
        return createErrorResponse(HttpStatus.valueOf(ex.getStatusCode()), ex.getMessage(), ex.getError());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGenericException(Exception ex) {
        logger.error("Unexpected Exception: {}", ex.getMessage(), ex);
        return createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred", ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        String errorMessages = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return createErrorResponse(HttpStatus.BAD_REQUEST, "Validation failed", errorMessages);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        String message = "A constraint violation occurred.";
        String errorDetails = ex.getRootCause() != null ? ex.getRootCause().getMessage() : ex.getMessage();

        if (ex.getCause() instanceof ConstraintViolationException constraintViolationException) {
            if (constraintViolationException.getConstraintName().contains("ws_user_email_key")) {
                message = "Email already exists";
                errorDetails = "A user with this email address already exists. Please choose a different email.";
            }
        }

        ApiErrorResponse response = new ApiErrorResponse(HttpStatus.CONFLICT.value(), message, errorDetails);

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        logger.error("Malformed JSON request: {}", ex.getMessage(), ex);
        return createErrorResponse(HttpStatus.BAD_REQUEST, "Malformed JSON request", ex.getLocalizedMessage());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiErrorResponse> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        logger.error("Missing request parameter: {}", ex.getMessage(), ex);
        return createErrorResponse(HttpStatus.BAD_REQUEST, "Missing required parameter",
                "Parameter '" + ex.getParameterName() + "' is required");
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        logger.error("HTTP method not supported: {}", ex.getMessage(), ex);
        return createErrorResponse(HttpStatus.METHOD_NOT_ALLOWED, "HTTP method not supported", ex.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleEntityNotFoundException(EntityNotFoundException ex) {
        logger.error("Entity not found: {}", ex.getMessage(), ex);
        return createErrorResponse(HttpStatus.NOT_FOUND, "Resource not found", ex.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiErrorResponse> handleAccessDeniedException(AccessDeniedException ex) {
        logger.error("Access denied: {}", ex.getMessage(), ex);
        return createErrorResponse(HttpStatus.FORBIDDEN, "Access denied", ex.getMessage());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        logger.error("No handler found: {}", ex.getMessage(), ex);
        return createErrorResponse(HttpStatus.NOT_FOUND, "No handler found for the request",
                "No mapping found for " + ex.getHttpMethod() + " " + ex.getRequestURL());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        logger.error("Illegal argument: {}", ex.getMessage(), ex);
        return createErrorResponse(HttpStatus.BAD_REQUEST, "Invalid argument provided", ex.getMessage());
    }

}