package com.example.demo.controller;

import com.example.demo.domain.Item;
import com.example.demo.requsetDTO.ItemInsertRequestDTO;
import com.example.demo.requsetDTO.LoginDTO;
import com.example.demo.responseDTO.LoginResponseDTO;
import com.example.demo.service.ItemService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    private final HttpSession session;

    @PostMapping("/insert")
    private String insertItem(@RequestBody ItemInsertRequestDTO requestDTO, HttpSession session) {
        LoginResponseDTO loginResponseDTO = (LoginResponseDTO)session.getAttribute("user");
        itemService.insertItem(requestDTO, loginResponseDTO);
        return "good";
    }


}
