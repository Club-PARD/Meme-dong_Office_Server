package com.wepard.meme_dong_office.controller.token.refresh;

import com.wepard.meme_dong_office.DTO.refreshToken.request.RefreshTokenRequestDTO;
import com.wepard.meme_dong_office.DTO.refreshToken.response.RefreshTokenResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class RefreshController {

    @PostMapping("/token/refresh")
    public ResponseEntity<?> getNewToken(@RequestBody RefreshTokenRequestDTO refreshTokenRequestDTO){

        RefreshTokenResponseDTO token = new RefreshTokenResponseDTO();
        token.setAccessToken("엑세스 토큰");
        token.setTokenType("bearer");
        token.setRefreshToken("리프레시 토큰");
        token.setExprTime(3600);
        token.setUserId(1L);

        return ResponseEntity.ok().body(token);
    }
}
