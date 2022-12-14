package hcmus.brightdemy.user.validation.validator;

import hcmus.brightdemy.user.entity.User;
import hcmus.brightdemy.user.repository.UserRepository;
import hcmus.brightdemy.user.validation.annotation.UniqueEmail;
import hcmus.brightdemy.user.validation.annotation.UniqueUsername;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;


public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
    @Autowired
    private UserRepository userRepository;

    private String message;

    @Override
    public void initialize(UniqueEmail uniqueEmail) {
        ConstraintValidator.super.initialize(uniqueEmail);
        this.message = uniqueEmail.message();
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        Optional<User> userOpt = userRepository.findByEmail(email);

        if(!userOpt.isPresent()) {
            return true;
        }

        context.buildConstraintViolationWithTemplate(this.message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();

        return false;
    }
}

