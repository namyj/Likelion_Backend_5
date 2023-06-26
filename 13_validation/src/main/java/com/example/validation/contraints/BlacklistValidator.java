package com.example.validation.contraints;

import com.example.validation.contraints.annotations.BlackList;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.ConstraintValidator;

import java.util.HashSet;
import java.util.Set;

public class BlacklistValidator implements ConstraintValidator<BlackList,String>  {
    private Set<String> blacklist;

    // 어노테이션을 사용할 때 blacklist에 포함시킬 값들을 전달할 수 있다. 
    @Override
    public void initialize(BlackList constraintAnnotation) {
        blacklist = new HashSet<>();
        for (String target: constraintAnnotation.blacklist()) {
            blacklist.add(target);
        }
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // this.blacklist 안에 value가 있으면 실패
        return !this.blacklist.contains(value);
    }
}
