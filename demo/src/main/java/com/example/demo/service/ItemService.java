package com.example.demo.service;

import com.example.demo.domain.Item;
import com.example.demo.domain.User;
import com.example.demo.repository.ItemRepository;
import com.example.demo.requsetDTO.ItemInsertRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public String insertItem(ItemInsertRequestDTO requestDTO) {
        Item item = new Item(requestDTO.getName(),requestDTO.getPrice(),requestDTO.getStockQuantity(), requestDTO.getDescription());
        itemRepository.save(item);
        return "good";
    }

}
