package com.example.jpa.entities;

import lombok.Data;

@Data
public class StudentDto {
    private Long id;
    private String name;
    private String email;

    // 정적 팩토리 메소드 패턴
    public static StudentDto fromEntity(StudentEntity studentEntity) {
        StudentDto dto = new StudentDto();
        dto.setId(studentEntity.getId());
        dto.setName(studentEntity.getName());
        dto.setEmail(studentEntity.getEmail());

        return dto;
    }
}
