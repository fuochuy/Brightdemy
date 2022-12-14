package hcmus.brightdemy.role.validation.annotation;

import hcmus.brightdemy.role.validation.validator.UniqueRoleIdValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueRoleIdValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueRoleId {

    String message() default "Role Id is existed.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
