package hcmus.brightdemy.security.exception;

import io.jsonwebtoken.JwtException;

public class BrightdemyIllegalJwtException extends JwtException {

    private static final long serialVersionUID = 8105942879951389178L;

    public BrightdemyIllegalJwtException(String message) {
        super(message);
    }

}