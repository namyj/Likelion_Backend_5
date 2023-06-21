package com.example.student;

import com.example.student.dto.StudentDto;
import com.example.student.entity.StudentEntity;
import com.example.student.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentDto> readStudentAll() {
        List<StudentDto> studentDtoList = new ArrayList<>();
        List<StudentEntity> studentEntityList = this.studentRepository.findAll();

        for (StudentEntity studentEntity : studentEntityList) {
            studentDtoList.add(StudentDto.fromEntity(studentEntity));
        }
        return studentDtoList;
    }

    public StudentDto createStudent(StudentDto studentDto) {
        StudentEntity student = new StudentEntity();
        student.setId(studentDto.getId());
        student.setName(studentDto.getName());
        student.setAge(studentDto.getAge());
        student.setPhone(studentDto.getPhone());
        student.setEmail(studentDto.getEmail());

        return StudentDto.fromEntity(studentRepository.save(student));
    }

    public StudentDto readStudent(Long id) {
        Optional<StudentEntity> optionalEntity = studentRepository.findById(id);
        if (optionalEntity.isPresent()) return StudentDto.fromEntity(optionalEntity.get());
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public StudentDto updateStudent(Long id, StudentDto studentDto) {
        Optional<StudentEntity> optionalEntity = studentRepository.findById(id);

        if (optionalEntity.isPresent()) {
            StudentEntity targetEntity = optionalEntity.get();
            targetEntity.setName(studentDto.getName());
            targetEntity.setAge(studentDto.getAge());
            targetEntity.setPhone(studentDto.getPhone());
            targetEntity.setEmail(studentDto.getEmail());
            return StudentDto.fromEntity(studentRepository.save(targetEntity));
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public void deleteStudent(Long id) {

        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
