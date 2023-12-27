package com.wepard.meme_dong_office.dto.token.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class TokenResponseDTO {
    private String accessToken;
    private String tokenType;
    private String refreshToken;
    private Integer exprTime;
    private Long userId;

    @Builder
    public TokenResponseDTO(
            final String accessToken,
            final String tokenType,
            final String refreshToken,
            final Integer exprTime,
            final Long userId
    ){
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.refreshToken = refreshToken;
        this.exprTime = exprTime;
        this.userId = userId;
    }
}
