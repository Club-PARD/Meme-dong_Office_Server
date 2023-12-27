package com.wepard.meme_dong_office.dto.users.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class UsersResponseDTO {
    private Long id;
    private String email;
    private String name;

    @Builder
    public UsersResponseDTO(
            final Long id,
            final String email,
            final String name
    ){
        this.id = id;
        this.email = email;
        this.name = name;
    }
}
