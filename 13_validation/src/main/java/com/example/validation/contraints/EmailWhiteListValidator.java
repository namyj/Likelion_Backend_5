package com.example.validation.contraints;

import com.example.validation.contraints.annotations.EmailWhiteList;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.HashSet;
import java.util.Set;

// Email 유효성 검사를 수행하는 validator
public class EmailWhiteListValidator implements ConstraintValidator<EmailWhiteList, String> {
    private final Set<String> whiteList;

    public EmailWhiteListValidator() {
        this.whiteList = new HashSet<>();
        this.whiteList.add("gmail.com");
        this.whiteList.add("naver.com");
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // 유효한 값일 때 > true 반환, 유효하지 않은 값일 때 > false 반환
        String[] split = value.split("@");
        String domain = split[split.length -1];

        return whiteList.contains(domain);
    }
}
