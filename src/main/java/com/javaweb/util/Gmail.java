package com.javaweb.util;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = GmailValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Gmail {
    String message() default "Invalid gmail";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
