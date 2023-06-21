package com.example.student;

import com.example.student.dto.StudentDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("")
    public String home(Model model) {
        model.addAttribute("studentList", studentService.readStudentAll());
        return "home";
    }

    @GetMapping("/create-view")
    public String createView() {
        return "create";
    }

    @GetMapping("/create")
    public String create(StudentDto dto) {
        StudentDto newDto = studentService.createStudent(dto);
        return "redirect:/students/" + newDto.getId(); // PRG (Post Redirect Get)
    }

    @GetMapping("/{id}")
    public String read(@PathVariable("id") Long id, Model model) {
        model.addAttribute("student", studentService.readStudent(id));
        return "read";
    }

    @GetMapping("/{id}/update-view")
    public String updateView(@PathVariable("id") Long id, Model model) {
        model.addAttribute("student", studentService.readStudent(id));
        return "update";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Long id, StudentDto studentDto) {
        StudentDto updateDto = studentService.updateStudent(id, studentDto);
        return "redirect:/students/" + updateDto.getId();
    }

    @GetMapping("/{id}/delete-view")
    public String deleteView(@PathVariable("id") Long id, Model model) {
        model.addAttribute("student", studentService.readStudent(id));
        return "delete";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }





}
