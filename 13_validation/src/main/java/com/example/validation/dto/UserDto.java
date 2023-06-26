package com.example.validation.dto;

import com.example.validation.contraints.annotations.BlackList;
import com.example.validation.contraints.annotations.EmailWhiteList;
import com.example.validation.contraints.annotations.Phone010;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDto {
    private Long id;
    @NotBlank @Size(min = 3, message = "최소 3글자 이상이여야 합니다.") // 비어있지 않아야한다.
    @BlackList(blacklist = {"black", "list"})
    private String username;
    @Email @EmailWhiteList // 이메일 형식
    private String email;
    @NotNull @Phone010 // null이 아니여야 한다.
    private String phone;

    @NotNull @Min(value = 14, message = "최소 14살 이상이여야 합니다.")
    private Integer age;
    @Future // 미래 시간만 <> @Past 과거 시간만
    private LocalDate validUntil;

    // @NotNull
    // private String notNullString;
    // @NotEmpty
    // private String notEmptyString;
    // @NotBlank
    // private String notBlankString;
}
