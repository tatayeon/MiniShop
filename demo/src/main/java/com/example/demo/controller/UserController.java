package com.example.demo.controller;

import com.example.demo.requsetDTO.LoginDTO;
import com.example.demo.requsetDTO.RegisterDTO;
import com.example.demo.responseDTO.LoginResponseDTO;
import com.example.demo.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    private String register(@RequestBody RegisterDTO registerDTO) {
        userService.register(registerDTO);
        return "good";
    }

    @PostMapping("/login")
    private String login(@RequestBody LoginDTO loginDTO, HttpSession session) {
        LoginResponseDTO responseDTO =  userService.login(loginDTO);
        session.setAttribute("user", responseDTO); //일단 로그인 정보를 세션이 넣는 방식으로 진행
        return "good";

    }


}
