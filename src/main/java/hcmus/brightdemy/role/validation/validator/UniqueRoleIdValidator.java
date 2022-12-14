package hcmus.brightdemy.role.validation.validator;

import hcmus.brightdemy.role.entity.Role;
import hcmus.brightdemy.role.repository.RoleRepository;
import hcmus.brightdemy.role.validation.annotation.UniqueRoleId;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class UniqueRoleIdValidator implements ConstraintValidator<UniqueRoleId, String>{
    @Autowired
    private RoleRepository roleRepository;

    private String message;

    @Override
    public void initialize(UniqueRoleId uniqueRoleId) {
        ConstraintValidator.super.initialize(uniqueRoleId);
        this.message = uniqueRoleId.message();
    }

    @Override
    public boolean isValid(String roleId, ConstraintValidatorContext context) {
        Optional<Role> roleOpt = roleRepository.findById(roleId);

        if(!roleOpt.isPresent()) {
            return true;
        }

        context.buildConstraintViolationWithTemplate(this.message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();

        return false;
    }
}
