package ExceptionUtil;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler({ IllegalArgumentException.class, NullPointerException.class })
    public ResponseEntity<CustomErrorResponse> handleInvalidRequest(Exception ex) {
        String errorMessage = "Bad Request: " + ex.getMessage();
        CustomErrorResponse errorResponse = new CustomErrorResponse(errorMessage);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleProductNotFoundException(ProductNotFoundException ex) {
        String errorMessage = "Product not found: " + ex.getMessage();
        CustomErrorResponse errorResponse = new CustomErrorResponse(errorMessage);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
