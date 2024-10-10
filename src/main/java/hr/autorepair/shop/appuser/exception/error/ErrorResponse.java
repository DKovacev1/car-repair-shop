package hr.autorepair.shop.appuser.exception.error;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ErrorResponse {
    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    private ErrorResponse(Builder builder) {
        this.timestamp = builder.timestamp;
        this.status = builder.status;
        this.error = builder.error;
        this.message = builder.message;
        this.path = builder.path;
    }

    public static class Builder {
        private final LocalDateTime timestamp = LocalDateTime.now();
        private Integer status;
        private String error;
        private String message;
        private String path;

        public Builder httpStatus(HttpStatus httpStatus) {
            this.status = httpStatus.value();
            this.error = httpStatus.getReasonPhrase();
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder path(String path) {
            this.path = path;
            return this;
        }

        public ErrorResponse build() {
            return new ErrorResponse(this);
        }
    }

}
