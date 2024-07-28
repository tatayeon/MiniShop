package com.example.demo.controller;

import com.example.demo.requsetDTO.LoginDTO;
import com.example.demo.requsetDTO.RegisterDTO;
import com.example.demo.responseDTO.LoginResponseDTO;
import com.example.demo.service.UserService;
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
    private LoginResponseDTO login(@RequestBody LoginDTO loginDTO) {
        return userService.login(loginDTO);
    }


}
