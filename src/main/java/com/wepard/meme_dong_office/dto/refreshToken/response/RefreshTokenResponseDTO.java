package com.wepard.meme_dong_office.dto.refreshToken.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefreshTokenResponseDTO {
    private String accessToken;
    private String tokenType;
    private String refreshToken;
    private Integer exprTime;
    private Long userId;
}
