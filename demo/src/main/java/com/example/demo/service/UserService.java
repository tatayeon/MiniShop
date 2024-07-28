package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.requsetDTO.RegisterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void register(RegisterDTO registerDTO) {
        User user = new User(registerDTO.getNickName(), registerDTO.getPassword(), registerDTO.getEmail());
        validateDuplicateMember(user);
        userRepository.save(user);
    }
    //중복확인로직
    private void validateDuplicateMember(User user) {
        List<User> findUser = userRepository.findByNickName(user.getNickName());
        if (!findUser.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }


}
