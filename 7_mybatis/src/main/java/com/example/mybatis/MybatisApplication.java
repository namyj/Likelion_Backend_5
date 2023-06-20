package com.example.mybatis;

import com.example.mybatis.dao.StudentDao;
import com.example.mybatis.dao.StudentXmlDao;
import com.example.mybatis.model.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class MybatisApplication {

	public static void main(String[] args) {
		ApplicationContext ac = SpringApplication.run(MybatisApplication.class, args);

		// StudentDao dao = ac.getBean(StudentDao.class);
		StudentXmlDao dao = ac.getBean(StudentXmlDao.class);

		// insert
		Student student = new Student();
		student.setName("kim");
		student.setAge(40);
		student.setPhone("010-1111-2222");
		student.setEmail("kim@test.com");
		dao.createStudent(student);

		// select
		System.out.println(dao.readStudentAll());

		for (Long i = 1L; i <= 4L; i++) {
			System.out.println(dao.readStudent(i));
		}

		// update
		Student st = dao.readStudent(1L);
		if (st != null){
			st.setName("update_name");
			dao.updateStudent(st);
		}

		// Delete
		dao.deleteStudent(2L);
		dao.deleteStudent(3L);

		// Select with optional
		System.out.println(dao.selectStudentOptional(9999L).toString());

		// Insert into multi dynamic SQL foreach
		Student ash = new Student();
		ash.setName("ash");
		Student brock = new Student();
		brock.setName("brock");
		Student misty = new Student();
		misty.setName("misty");

		List<Student> batchStudents = new ArrayList<>();
		batchStudents.add(ash);
		batchStudents.add(brock);
		batchStudents.add(misty);
		dao.insertStudentBatch(batchStudents);

		// Select with where dynamic SQL if
		Student target = new Student();
		target.setName("alex");
		target.setAge(31);
		System.out.println(dao.findByFields(target).toString());
	}


}
