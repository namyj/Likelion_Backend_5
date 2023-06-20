package com.example.jpa;

import com.example.jpa.entities.StudentEntity;
import com.example.jpa.repos.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppService {
    private final AppRepository repository;
    private final StudentRepository studentRepository;

    public AppService(AppRepository repository, StudentRepository studentRepository) {
        this.repository = repository;
        this.studentRepository = studentRepository;
    }

    // create
    public void createStudent(
        String name,
        Integer age,
        String phone,
        String email
    ) {
        StudentEntity newEntity = new StudentEntity();
        newEntity.setName(name);
        newEntity.setAge(age);
        newEntity.setPhone(phone);
        newEntity.setEmail(email);
        this.studentRepository.save(newEntity);
    }

    // read
    public void readStudent(Long id) {
        Optional<StudentEntity> optionalStudentEntity = this.studentRepository.findById(id);

        // 1. 실제 데이터가 있을 때
        if (optionalStudentEntity.isPresent()) {
            System.out.println(optionalStudentEntity.get());
        } else {
            System.out.println("no result");
        }
    }

    // read all
    public void readStudentAll() {
        System.out.println(this.studentRepository.findAll());
    }

    // update
    public void updateStudent(
            Long id,
            String name
    ) {
        Optional<StudentEntity> optionalStudentEntity = this.studentRepository.findById(id);
        if (optionalStudentEntity.isPresent()) {
            StudentEntity target = optionalStudentEntity.get();
            target.setName(name);
            this.studentRepository.save(target);
        } else {
            System.out.println("could not found");
        }
    }

    // delete
    public void deleteStudent(Long id) {
        Optional<StudentEntity> optionalStudentEntity = this.studentRepository.findById(id);
        if (optionalStudentEntity.isPresent()) {
            StudentEntity studentEntity = optionalStudentEntity.get();
            this.studentRepository.delete(studentEntity);
        } else {
            System.out.println("could not found");
        }
    }


    // findAllBy
    public void findAllByTest() {
        System.out.println("findAllByOrderByName");
        List<StudentEntity> studentEntities = this.studentRepository.findAllByOrderByName();
        for (int i = 0; i < 5; i++) {
            System.out.println(studentEntities.get(i));
        }
        System.out.println("....");
        System.out.println("findAllByOrderByAgeDesc");
        studentEntities =
                this.studentRepository.findAllByOrderByAgeDesc();
        for (int i = 0; i < 5; i++) {
            System.out.println(studentEntities.get(i));
        }
        System.out.println("...");

        System.out.println("findAllByAgeLessThan");
        studentEntities =
                this.studentRepository.findAllByAgeLessThan(21);
        for (int i = 0; i < 5; i++) {
            System.out.println(studentEntities.get(i));
        }
        System.out.println("...");

        System.out.println("findAllByPhoneStartingWith");
        studentEntities =
                this.studentRepository.findAllByPhoneStartingWith("010-");
        for (int i = 0; i < 5; i++) {
            System.out.println(studentEntities.get(i));
        }
        System.out.println("...");
    }


}
