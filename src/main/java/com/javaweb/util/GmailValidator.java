package com.javaweb.util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class GmailValidator implements ConstraintValidator<Gmail, String> {
    @Override
    public void initialize(Gmail gmail) {
    }

    @Override
    public boolean isValid(String gmail, ConstraintValidatorContext cxt) {
        if (gmail == null) {
            return false;
        }
        else {
            if (gmail.matches("[a-zA-Z0-9.]+@[a-zA-Z0-9.]+\\.[a-zA-Z]{2,}")) return true;
            else return false;
        }
    }
}
