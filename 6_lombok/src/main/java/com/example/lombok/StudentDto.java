package com.example.lombok;

import lombok.*;

/**
 * Lombok의 어노테이션을 사용 > 자동으로 Getter, Setter, Constructor 등 생성
 */
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class StudentDto {
    private Long id;
    private String name;
    private String email;
}
