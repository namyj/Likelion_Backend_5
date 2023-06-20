package com.example.mybatis.mapper;

import com.example.mybatis.model.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentMapper {
    @Insert("insert into students(name, age, phone, email)" +
            "values (#{name}, #{age}, #{phone}, #{email})")
    void insertStudent(Student student);

    @Select("select * from students")
    List<Student> selectStudentAll();

    @Select("select * from students where id = #{id}")
    Student selectStudent(Long id);

    @Update("update students set" +
            "name = #{name}" +
            "phone = #{phone}" +
            "email = #{email}" +
            "where id = #{id}")
    void updateStudent(Student student);

    @Delete("delete from students where id = #{id}")
    void deleteStudent(Long id);
}
