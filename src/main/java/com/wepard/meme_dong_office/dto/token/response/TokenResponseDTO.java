package com.wepard.meme_dong_office.dto.token.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TokenResponseDTO {
    private String accessToken;
    private String tokenType;
    private Integer exprTime;
    private Long userId;

    @Builder
    public TokenResponseDTO(
            final String accessToken,
            final String tokenType,
            final Integer exprTime,
            final Long userId
    ){
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.exprTime = exprTime;
        this.userId = userId;
    }
}
