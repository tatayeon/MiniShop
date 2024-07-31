package com.example.demo.controller;

import com.example.demo.requsetDTO.LoginDTO;
import com.example.demo.requsetDTO.RegisterDTO;
import com.example.demo.responseDTO.LoginResponseDTO;
import com.example.demo.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
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
    public String login(@RequestBody LoginDTO loginDTO, HttpSession session) {
        LoginResponseDTO loginresponseDTO =  userService.login(loginDTO);
        session.setAttribute("user", loginresponseDTO); //일단 로그인 정보를 세션이 넣는 방식으로 진행
        return "good";
    }
    @GetMapping("/login")
    public String login() {
        return "User/login.html";
    }


}
