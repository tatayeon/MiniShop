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
        return "good";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginDTO loginDTO, HttpSession session) {
        LoginResponseDTO loginresponseDTO =  userService.login(loginDTO);
        session.setAttribute("user", loginresponseDTO); //일단 로그인 정보를 세션이 넣는 방식으로 진행
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "user/login.html";
    }




}
