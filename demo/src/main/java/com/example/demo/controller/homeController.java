package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class homeController {

    @RequestMapping("/")
    public String home() {
        log.info("home");
        return "home";
    }

//    @GetMapping("user/login")
//    public String login() {
//        return "user/login.html";
//    }


}
