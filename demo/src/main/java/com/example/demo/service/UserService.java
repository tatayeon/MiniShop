package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.requsetDTO.LoginDTO;
import com.example.demo.requsetDTO.RegisterDTO;
import com.example.demo.responseDTO.LoginResponseDTO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.LoginException;
import java.util.List;
import java.util.Objects;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private HttpSession session;

    @Transactional
    public void register(RegisterDTO registerDTO) {
        User user = new User(registerDTO.getNickName(), registerDTO.getPassword(), registerDTO.getEmail());
        validateDuplicateMember(user);
        userRepository.save(user);
    }

    @Transactional
    public LoginResponseDTO login(LoginDTO loginDTO) {
        User user = userRepository.findByNickNameForLogin(loginDTO.getNickName());

        if (user != null && user.getPassword().equals(loginDTO.getPassword())) {
            // 성공 응답 생성
            return new LoginResponseDTO(user.getId().toString(), user.getNickName(), true);
        } else {
            // 실패 응답 생성
            throw new IllegalStateException("로그인 실패");
        }
    }


    //중복확인로직
    private void validateDuplicateMember(User user) {
        List<User> findUser = userRepository.findByNickName(user.getNickName());
        if (!findUser.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }



}
