package com.example.demo.requsetDTO;

import com.example.demo.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemInsertRequestDTO {

    private String name;

    private int price;

    private int stockQuantity;

    private String description;

    private User user;

}
