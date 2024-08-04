package com.example.demo.controller;

import com.example.demo.domain.Item;
import com.example.demo.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Slf4j
public class homeController {

    private final ItemService itemService;

    public homeController(ItemService itemService) {
        this.itemService = itemService;
    }

    @RequestMapping("/")
    public String home(Model model) {
        List<Item> item = itemService.findItemList();
        model.addAttribute("item", item);
        log.info("home");
        return "home";
    }

//    @GetMapping("user/login")
//    public String login() {
//        return "user/login.html";
//    }


}
