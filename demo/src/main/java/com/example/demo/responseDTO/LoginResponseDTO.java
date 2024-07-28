package com.example.demo.responseDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginResponseDTO {

    private String id;

    private String nickName;

    private boolean loginStatus = false;

    public LoginResponseDTO(String id, String nickName, boolean loginStatus) {
        this.id = id;
        this.nickName = nickName;
        this.loginStatus = loginStatus;
    }

}

