package com.wepard.meme_dong_office.DTO.token.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenResponseDTO {
    private String accessToken;
    private String tokenType;
    private String refreshToken;
    private Integer exprTime;
    private Long userId;
}
