package com.example.jpa;

import com.example.jpa.entities.StudentDto;
import com.example.jpa.entities.StudentEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/students")
public class AppController {
    private final AppService service;

    public AppController(AppService service) {
        this.service = service;
    }

    @GetMapping("home")
    public String home(Model model) {
        model.addAttribute("studentList", service.readStudentAll());
        return "home";
    }

    @GetMapping("create")
    public @ResponseBody String create() {
        this.service.createStudent(
                "alex",
                35,
                "010-1111-2222",
                "alex@test.com"
        );

        return "done-create";
    }

    @GetMapping("read-all")
    public @ResponseBody List<StudentDto> readAll() {
        return this.service.readStudentAll();
    }


    @GetMapping("read")
    public @ResponseBody String readOne() {
        this.service.readStudent(1L); // 우선 고정값으로
        return "done-read-one";
    }

    @GetMapping("update")
    public @ResponseBody String update() {
        this.service.updateStudent(1L, "alexander");
        return "done-update";
    }

    @GetMapping("delete")
    public @ResponseBody String delete() {
        this.service.deleteStudent(1L);
        return "done-delete";
    }
}
