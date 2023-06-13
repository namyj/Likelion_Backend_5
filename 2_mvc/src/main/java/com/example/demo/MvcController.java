package com.example.demo;

import com.example.demo.model.LottoService;
import com.example.demo.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class MvcController {

    // final 선언 > 추후 해당 객체를 사용하는 클래스에서는 값 변경 불가능 (불변 객체)
    private final LottoService lottoService;

    public MvcController(LottoService lottoService) {
        this.lottoService = lottoService;
    }

    @RequestMapping("/hits")
    public String hits(Model model) {
        int hitCount = lottoService.addHit();
        model.addAttribute("hits", ++hitCount);
        return "hits";
    }

    @RequestMapping("/lotto")
    public String lotto(Model model) {
        model.addAttribute("listOfNums", lottoService.nextLottoNumber());
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
