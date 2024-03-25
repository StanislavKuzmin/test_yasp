package com.github.stanislav.kuzmin.yasp.util.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StringEnumerationValidator implements ConstraintValidator<StringEnumeration, String> {

    private String[] allowedValues;

    @Override
    public void initialize(StringEnumeration constraintAnnotation) {
        allowedValues = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        for (String allowedValue : allowedValues) {
            if (allowedValue.equals(value)) {
                return true;
            }
        }
        return false;
    }
}
