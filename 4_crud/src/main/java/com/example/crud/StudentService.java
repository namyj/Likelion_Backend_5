package com.example.crud;

import com.example.crud.model.StudentDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Student 를 생성, 삭제, 읽기 등을 수행
 */
@Service
public class StudentService {
    private final List<StudentDto> studentList = new ArrayList<>();
    private Long nextId = 1L;

    public StudentService() {
        createStudent("alex", "alex@gmail.com");
        createStudent("brad", "brad@gmail.com");
        createStudent("chad", "chad@gmail.com");
    }

    // 새로운 studentDto 생성
    public StudentDto createStudent(String name, String email) {
        StudentDto newStudent = new StudentDto(nextId, name, email);
        nextId++;
        studentList.add(newStudent);
        return newStudent;
    }

    // 모든 studentDto 읽기
    public List<StudentDto> readStudentAll() {
        return studentList;
    }
    
    // id에 해당하는 studentDto 읽기
    public StudentDto readStudent(Long id) {
        for (StudentDto studentDto:studentList) {
            if (studentDto.getId().equals(id)) return studentDto;
        }

        return null;
    }
    

}
