package com.example.crud;

import com.example.crud.model.StudentDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/create-view")
    public String creatView() {
        return "create";
    }

    @PostMapping("/create")
    public String creat(
            @RequestParam("name") String name,
            @RequestParam("email") String email
    ) {
        StudentDto studentDto = studentService.createStudent(name, email);
        System.out.println(studentDto.toString());
        return "redirect:/create-view";
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute(
                "studentList",
                studentService.readStudentAll()
        );
        return "home";
    }

}
