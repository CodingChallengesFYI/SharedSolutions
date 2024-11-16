package com.tinyurl.api.userservice.validations;

import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
public @interface EmailOrUsername {

    String message() default "Either email or username must be provided, but not both";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
