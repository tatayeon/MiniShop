package com.example.demo.responseDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginResponseDTO {

    private String id;

    private String nickName;

    public LoginResponseDTO(String id, String nickName) {
        this.id = id;
        this.nickName = nickName;
    }

}

