package com.example.demo.init;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInit {

    private final UserRepository userRepository;

    @PostConstruct
    public void init() {
        User user1 = new User("user1", "1234", "test@naver.com");
        User user = new User("user2", "5678", "test@naver.com");

        userRepository.save(user1);
        userRepository.save(user);
    }
}
