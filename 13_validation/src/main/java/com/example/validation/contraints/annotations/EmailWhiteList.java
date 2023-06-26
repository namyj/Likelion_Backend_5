package com.example.validation.contraints.annotations;

import com.example.validation.contraints.EmailWhiteListValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 사용자 지정 어노테이션

@Target(ElementType.FIELD) // 어노테이션을 어디에 적용할지
@Retention(RetentionPolicy.RUNTIME) // 어노테이션을 언제까지 유지할지
@Constraint(validatedBy = EmailWhiteListValidator.class)
public @interface EmailWhiteList {
    // Annotation Element
    String message() default "email not in whitelist";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
