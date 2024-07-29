package com.example.demo.responseDTO;


import lombok.Builder;
import lombok.Getter;

@Getter
public class ItemDetailDTO {

    private String title;

    private int price;

    private int stockQuantity;

    private String description;

    private String witer;

    @Builder
    public ItemDetailDTO(String title, int price, int stockQuantity, String description, String witer) {
        this.title = title;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.description = description;
        this.witer = witer;
    }

}
