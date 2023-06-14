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
        System.out.println(studentList.toString());
        return studentList;
    }
    
    // id에 해당하는 studentDto 읽기
    public StudentDto readStudent(Long id) {
        for (StudentDto studentDto:studentList) {
            if (studentDto.getId().equals(id)) return studentDto;
        }

        return null;
    }

    public StudentDto readStudent2(Long id) {
        return studentList
                .stream()
                .filter(studentDto -> studentDto.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // 학생 데이터 갱신
    public StudentDto updateStudent(Long id, String name, String email) {
        StudentDto targetDto = this.readStudent(id);
        if(targetDto != null) {
            targetDto.setName(name);
            targetDto.setEmail(email);
            return targetDto;
        } else return null;
    }

    // 학생 데이터 삭제
    public boolean deleteStudent(Long id) {
        int target = -1;
        // 학생 인덱스 검색
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getId().equals(id)) {
                target = i;
                break;
            }
        }

        // 학생 삭제
        if (target > -1) {
            studentList.remove(target);
            return true;
        } else return false;
    }

}
