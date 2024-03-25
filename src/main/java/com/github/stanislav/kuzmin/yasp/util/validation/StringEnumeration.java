package com.github.stanislav.kuzmin.yasp.util.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = StringEnumerationValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface StringEnumeration {
    String message() default "Invalid name of column or sorting order";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String[] value() default {};
}
