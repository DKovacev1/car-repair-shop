package hr.autorepair.shop.exception.handler;

import hr.autorepair.shop.exception.error.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;

@ControllerAdvice
public class CustomExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(value = {SQLException.class, Exception.class, RuntimeException.class, SQLSyntaxErrorException.class, BadSqlGrammarException.class})
    public ResponseEntity<ErrorResponse> handleExceptions(Exception ex, HttpServletRequest request){
        printStackTrace(ex);
        logger.error("Unexpected error has occurred!");
        logger.error(ex.toString());

        ErrorResponse errorResponse = new ErrorResponse.Builder()
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .message("Unexpected error has occurred. Please, contact the administrator.")
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.internalServerError().body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {
        printStackTrace(ex);
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
        printStackTrace(ex);
        ErrorResponse errorResponse = new ErrorResponse.Builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoResourceFoundException(NoResourceFoundException ex, HttpServletRequest request) {
        printStackTrace(ex);
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse> handleResponseStatusException(ResponseStatusException ex, HttpServletRequest request){
        printStackTrace(ex);
        ErrorResponse errorResponse = new ErrorResponse.Builder()
                .httpStatus(HttpStatus.valueOf(ex.getStatusCode().value()))
                .message(ex.getLocalizedMessage())
                .path(request.getRequestURI())
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(ex.getStatusCode().value()));
    }

    private void printStackTrace(Exception ex){
        ex.printStackTrace();
    }

}
