package com.example.demo;

import com.example.demo.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class MvcController {

    private int hitCount = 0;

    @RequestMapping("/hits")
    public String hits(Model model) {
        model.addAttribute("hits", ++hitCount);
        return "hits";
    }

    @RequestMapping("/lotto")
    public String lotto(Model model) {
        List<Integer> listOfNums = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            listOfNums.add((int) (Math.random() * 60));
        }

        model.addAttribute("listOfNums", listOfNums);
        return "lotto";
    }

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute(
            "message",
            "Hello thymeleaf!"
        );
        return "home";
    }

    @RequestMapping("/student")
    public String student(Model model) {
        model.addAttribute(
                "object",
                new Student("Kim", "abcd@email.com")
        );
        return "student";
    }

    @RequestMapping("/is-logged-in")
    public String isLoggedIn(Model model) {
        // model.addAttribute("isLoggedIn", true); // 로그인 했을 때
        model.addAttribute("isLoggedIn", false); // 로그인 안했을 때
        return "login";
    }


    @RequestMapping("/each")
    public String items(Model model) {
        // 문자열 반복
        List<String> listOfStrings = new ArrayList<>();
        listOfStrings.add("foo");
        listOfStrings.add("bar");
        listOfStrings.add("baz");
        model.addAttribute("listOfStrings", listOfStrings);
        
        // 객체 반복
        List<Student> studentList = Arrays.asList(
                new Student("Kim", "aaa@email.com"),
                new Student("Kang", "bbb@email.com"),
                new Student("Koo", "ccc@email.com")
        );
        model.addAttribute("studentList", studentList);

        return "items";
    }

}
