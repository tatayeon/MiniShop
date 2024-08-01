package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.requsetDTO.LoginDTO;
import com.example.demo.requsetDTO.RegisterDTO;
import com.example.demo.responseDTO.LoginResponseDTO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.LoginException;
import java.util.List;
import java.util.Objects;

import static org.hibernate.query.sqm.tree.SqmNode.log;
@Slf4j
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
        User user = userRepository.findByEmail(loginDTO.getEmail());

        log.info(user.getEmail());


        if (user != null && user.getPassword().equals(loginDTO.getPassword())) {
            // 성공 응답 생성
            return new LoginResponseDTO(user.getId(), user.getEmail(), true);
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
