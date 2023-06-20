package com.example.jpa.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="students") // name을 지정하지 않으면, student_entity
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Collumn 어노테이션
    // 테이블 Constraint를 추가할 수 있다.
    // @Column(name = "username", nullable = false, unique = true)
    private String name;
    private Integer age;
    private String phone;
    private String email;

}
