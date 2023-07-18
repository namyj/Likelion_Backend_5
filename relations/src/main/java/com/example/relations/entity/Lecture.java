package com.example.relations.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    // @JoinColumn(name = "instructor") // 외래키 매핑 할때 사용, name 속성에 매핑할 외래 키 이름을 지정
    private Instructor instructor;

    @ManyToMany(mappedBy = "attending")
    private List<Student> students;

    private String courseName;
    private String day;
    private Integer startTime;
    private Integer endTime;
}
