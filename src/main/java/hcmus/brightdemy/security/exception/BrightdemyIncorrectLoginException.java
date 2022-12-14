package hcmus.brightdemy.security.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class BrightdemyIncorrectLoginException extends UsernameNotFoundException {

    private static final long serialVersionUID = -1045601268219625591L;


    public BrightdemyIncorrectLoginException(String message) {
        super(message);
    }

}