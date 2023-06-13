package com.example.form;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class FormController {
    @RequestMapping("/")
    public String home() {
        return "home";
    }

    // @RequestMapping(value="/send", method = RequestMethod.GET)
    @GetMapping("/send")
    public String getForm() {
        return "send";
    }

    // @RequestMapping(method = RequestMethod.POST,value="/receive")
    @PostMapping("/receive") // POST 요청만 받아들이도록
    public String receive(@RequestParam("msg") String msg, @RequestParam("email") String email) {
        System.out.println(msg);
        System.out.println(email);
        return "send";
    }
}
