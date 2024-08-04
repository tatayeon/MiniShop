package com.example.demo.init;

import com.example.demo.domain.Item;
import com.example.demo.domain.User;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInit {

    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    @PostConstruct
    public void init() {
        User user1 = new User("user1", "1234", "test1@naver.com");
        User user = new User("user2", "5678", "test2@naver.com");

        userRepository.save(user1);
        userRepository.save(user);

        Item item = new Item("가방", 1232, 200, "더 이뻐요", user);
        Item item1 = new Item("티셔츠", 123233, 300, "엄청 이뻐요", user);
        Item item2 = new Item("신발", 12553, 200, "진짜 이뻐요", user);
        Item item3 = new Item("바지", 126763, 100, "정말 이뻐요", user1);
        Item item4 = new Item("모자", 125653, 250, "덜 이뻐요", user1);
        Item item5 = new Item("맨투맨", 15423, 300, "진짜 많이이뻐요", user1);
        Item item6 = new Item("바지", 124563, 400, "그냥 이뻐요", user);

        itemRepository.save(item);
        itemRepository.save(item1);
        itemRepository.save(item2);
        itemRepository.save(item3);
        itemRepository.save(item4);
        itemRepository.save(item5);
        itemRepository.save(item6);



    }
}
