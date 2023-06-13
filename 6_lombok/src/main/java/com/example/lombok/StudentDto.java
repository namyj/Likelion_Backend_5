package com.example.lombok;

import lombok.*;

/**
 * Lombok의 어노테이션을 사용해서 생성 가능
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
