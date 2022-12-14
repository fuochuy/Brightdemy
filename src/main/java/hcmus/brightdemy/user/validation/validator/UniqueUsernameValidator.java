package hcmus.brightdemy.user.validation.validator;

import hcmus.brightdemy.user.entity.User;
import hcmus.brightdemy.user.repository.UserRepository;
import hcmus.brightdemy.user.validation.annotation.UniqueUsername;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {
    @Autowired
    private UserRepository userRepository;

    private String message;

    @Override
    public void initialize(UniqueUsername uniqueUsername) {
        ConstraintValidator.super.initialize(uniqueUsername);
        this.message = uniqueUsername.message();
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        Optional<User> userOpt = userRepository.findByUsername(username);

        if(!userOpt.isPresent()) {
            return true;
        }

        context.buildConstraintViolationWithTemplate(this.message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();

        return false;
    }
}
