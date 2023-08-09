package com.bookstore.common.annotation;

import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Target({FIELD, METHOD})
public @interface Phone {
    String message() default"{phone}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[]  payload() default {};
}
