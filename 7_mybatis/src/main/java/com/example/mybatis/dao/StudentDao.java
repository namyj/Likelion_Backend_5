package com.example.mybatis.dao;

import com.example.mybatis.mapper.StudentMapper;
import com.example.mybatis.mapper.StudentXmlMapper;
import com.example.mybatis.model.Student;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDao {
    private final SqlSessionFactory SessionFactory;

    public StudentDao(SqlSessionFactory sessionFactory) {
        this.SessionFactory = sessionFactory;
    }

    // 모든 학생을 조회하는 기능
    public List<Student> readStudentAll() {
        try(SqlSession sqlSession = SessionFactory.openSession()) {
            StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
            return studentMapper.selectStudentAll();
        }
    }

    // 학생을 생성하는 기능
    // SqlSession으로 데이터베이스 세션을 열고 > StudentMapper를 얻어온다
    // > insertStudent() 메서드를 호출
    public void createStudent(Student student) {
        try(SqlSession sqlSession = SessionFactory.openSession()) {
            StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
            studentMapper.insertStudent(student);
        }
    }

    // 특정 학생 조회 기능
    public Student readStudent(Long id) {
        try(SqlSession sqlSession = SessionFactory.openSession()) {
            StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
            return studentMapper.selectStudent(id);
        }
    }

    public List<Student> readAllXmlStudent() {
        try(SqlSession sqlSession = SessionFactory.openSession()) {
            StudentXmlMapper studentXmlMapper = sqlSession.getMapper(StudentXmlMapper.class);
            return studentXmlMapper.selectStudentAll();
        }
    }
}
