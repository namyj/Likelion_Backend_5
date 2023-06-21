package com.example.student.dto;

import com.example.student.entity.StudentEntity;
import lombok.Data;

@Data
public class StudentDto {
    private Long id;
    private String name;
    private Integer age;
    private String phone;
    private String email;

    // static factory method pattern
    public static StudentDto fromEntity(StudentEntity studentEntity) {
        StudentDto dto = new StudentDto();
        dto.setId(studentEntity.getId());
        dto.setName(studentEntity.getName());
        dto.setAge(studentEntity.getAge());
        dto.setPhone(studentEntity.getPhone());
        dto.setEmail(studentEntity.getEmail());

        return dto;
    }
}
