package hcmus.brightdemy.role.validation.annotation;

import hcmus.brightdemy.role.validation.validator.ExistedRoleIdValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Constraint(validatedBy = ExistedRoleIdValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistedRoleId {
    String message() default "Role Id is not existed. ";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
