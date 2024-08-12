package com.example.demo.controller;

import com.example.demo.domain.Item;
import com.example.demo.requsetDTO.ItemInsertRequestDTO;
import com.example.demo.requsetDTO.LoginDTO;
import com.example.demo.responseDTO.ItemDetailDTO;
import com.example.demo.responseDTO.LoginResponseDTO;
import com.example.demo.service.ItemService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    private final HttpSession session;

    @PostMapping("/insert")
    private String insertItem(@RequestBody ItemInsertRequestDTO requestDTO, HttpSession session,
                              @RequestPart(value = "files", required = false) List<MultipartFile> files) {
        LoginResponseDTO loginResponseDTO = (LoginResponseDTO)session.getAttribute("user");
        itemService.insertItem(requestDTO, loginResponseDTO, files);
        return "good";
    }

    @GetMapping("/show/detail/{itemId}")
    public ItemDetailDTO showDetail(@PathVariable("itemId") Long itemId, HttpSession session){

        LoginResponseDTO loginResponseDTO = (LoginResponseDTO)session.getAttribute("user");
        return itemService.showDetail(itemId, loginResponseDTO.getId());
    }

}
