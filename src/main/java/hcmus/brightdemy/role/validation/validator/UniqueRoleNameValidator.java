package hcmus.brightdemy.role.validation.validator;

import hcmus.brightdemy.role.entity.Role;
import hcmus.brightdemy.role.repository.RoleRepository;
import hcmus.brightdemy.role.validation.annotation.UniqueRoleName;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class UniqueRoleNameValidator implements ConstraintValidator<UniqueRoleName, String> {
    @Autowired
    private RoleRepository roleRepository;

    private String message;

    @Override
    public void initialize(UniqueRoleName uniqueRoleName) {
        ConstraintValidator.super.initialize(uniqueRoleName);
        this.message = uniqueRoleName.message();
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        Optional<Role> loaiNguoiDungOpt = roleRepository.findByName(name);

        if(!loaiNguoiDungOpt.isPresent()) {
            return true;
        }

        context.buildConstraintViolationWithTemplate(this.message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();

        return false;
    }


}
