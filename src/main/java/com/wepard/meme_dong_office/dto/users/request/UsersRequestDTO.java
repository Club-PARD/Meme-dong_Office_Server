package com.wepard.meme_dong_office.dto.users.request;

import lombok.Getter;

@Getter
public class UsersRequestDTO {
    private String email;
    private String password;
    private String name;

    public void updatePassword(
            final String newPassword
    ){
        this.password = newPassword;
    }
}
