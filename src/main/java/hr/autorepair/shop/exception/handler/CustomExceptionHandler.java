package hr.autorepair.shop.exception.handler;

import hr.autorepair.shop.exception.error.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {
        FieldError firstError = ex.getBindingResult().getFieldErrors().stream().findFirst().orElse(null);
        String message;
        if (firstError != null)
            message = firstError.getDefaultMessage();
        else
            message = "Validation error occurred.";

        ErrorResponse errorResponse = new ErrorResponse.Builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message(message)
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(HttpMessageNotReadableException ex, HttpServletRequest request) {

        ErrorResponse errorResponse = new ErrorResponse.Builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse> handleResponseStatusException(ResponseStatusException ex, HttpServletRequest request){
        ErrorResponse errorResponse = new ErrorResponse.Builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message(ex.getLocalizedMessage())
                .path(request.getRequestURI())
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(ex.getStatusCode().value()));
    }

}