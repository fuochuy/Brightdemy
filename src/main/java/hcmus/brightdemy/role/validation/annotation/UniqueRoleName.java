package hcmus.brightdemy.role.validation.annotation;

import hcmus.brightdemy.role.validation.validator.UniqueRoleIdValidator;
import hcmus.brightdemy.role.validation.validator.UniqueRoleNameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueRoleNameValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueRoleName {

    String message() default "Role Name is existed.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
