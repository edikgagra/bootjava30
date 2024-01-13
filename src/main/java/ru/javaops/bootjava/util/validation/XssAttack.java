package ru.javaops.bootjava.util.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = XssValidator.class)
@Target({METHOD, FIELD})
@Retention(RUNTIME)
public @interface XssAttack {
    String message() default "{exception.noHtml}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
