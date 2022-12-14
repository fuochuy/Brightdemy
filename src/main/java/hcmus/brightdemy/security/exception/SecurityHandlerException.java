package hcmus.brightdemy.security.exception;

import hcmus.brightdemy.common.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SecurityHandlerException {

    @ExceptionHandler(value = {BrightdemyIncorrectLoginException.class})
    public Object handleMovieIllegalJwtException(BrightdemyIncorrectLoginException e) {
        return ResponseHandler.getErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {BrightdemyIllegalJwtException.class})
    public Object handleMovieIllegalJwtException(BrightdemyIllegalJwtException e) {
        return ResponseHandler.getErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
