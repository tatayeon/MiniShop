package com.example.demo.service;

import com.example.demo.domain.Item;
import com.example.demo.domain.User;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.requsetDTO.ItemInsertRequestDTO;
import com.example.demo.requsetDTO.LoginDTO;
import com.example.demo.responseDTO.ItemDetailDTO;
import com.example.demo.responseDTO.LoginResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    @Transactional
    public String insertItem(ItemInsertRequestDTO requestDTO, LoginResponseDTO loginDTO) {
        List<User> user = userRepository.findByNickName(loginDTO.getNickName());

        Item item = Item.builder()
                .name(requestDTO.getName())
                .price(requestDTO.getPrice())
                .stockQuantity(requestDTO.getStockQuantity())
                .description(requestDTO.getDescription())
                .build();

        item.setUser(user.get(0));
        itemRepository.save(item);
        return "good";
    }

    @Transactional
    public ItemDetailDTO showDetail(Long itemId, Long userId){
        Item item = itemRepository.findById(itemId).orElse(null);

        User user = userRepository.findById(userId).orElse(null);

        return ItemDetailDTO.builder()
                .title(item.getName())
                .price(item.getPrice())
                .stockQuantity(item.getStockQuantity())
                .description(item.getDescription())
                .witer(user.getNickName())
                .build();
    }

}
