package hcmus.brightdemy.role.validation.validator;

import hcmus.brightdemy.role.entity.Role;
import hcmus.brightdemy.role.repository.RoleRepository;
import hcmus.brightdemy.role.validation.annotation.ExistedRoleId;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class ExistedRoleIdValidator implements ConstraintValidator<ExistedRoleId, String> {

    @Autowired
    private RoleRepository roleRepository;

    private String message;

    @Override
    public void initialize(ExistedRoleId existedRoleId) {
        ConstraintValidator.super.initialize(existedRoleId);
        this.message = existedRoleId.message();
    }

    @Override
    public boolean isValid(String roleId, ConstraintValidatorContext context) {
        Optional<Role> roleOpt = roleRepository.findById(roleId);

        if(roleOpt.isPresent()) {
            return true;
        }

        context.buildConstraintViolationWithTemplate(this.message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();

        return false;
    }


}
