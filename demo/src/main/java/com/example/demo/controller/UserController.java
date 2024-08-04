package com.example.demo.controller;

import com.example.demo.requsetDTO.LoginDTO;
import com.example.demo.requsetDTO.RegisterDTO;
import com.example.demo.responseDTO.LoginResponseDTO;
import com.example.demo.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterDTO registerDTO) {
        userService.register(registerDTO);
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDTO loginDTO, HttpSession session) {
        System.out.println("Email: " + loginDTO.getEmail()); // 디버깅용 출력
        System.out.println("Password: " + loginDTO.getPassword());

        LoginResponseDTO loginresponseDTO =  userService.login(loginDTO);
        session.setAttribute("user", loginresponseDTO); //일단 로그인 정보를 세션이 넣는 방식으로 진행
        return "redirect:/test.html";

    }

    @GetMapping("/login")
    public String login() {
        return "user/login.html";
    }




}
