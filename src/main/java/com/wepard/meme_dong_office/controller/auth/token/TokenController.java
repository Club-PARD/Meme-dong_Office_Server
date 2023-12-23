package com.wepard.meme_dong_office.controller.auth.token;

import com.wepard.meme_dong_office.DTO.token.request.TokenRequestDTO;
import com.wepard.meme_dong_office.DTO.token.response.TokenResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class TokenController {

    @PostMapping("/token")
    public ResponseEntity<?> getToken(@RequestBody TokenRequestDTO tokenRequestDTO){

        TokenResponseDTO token = new TokenResponseDTO();
        token.setAccessToken("엑세스 토큰");
        token.setTokenType("bearer");
        token.setRefreshToken("리프레시 토큰");
        token.setExprTime(3600);
        token.setUserId(1L);

        return ResponseEntity.ok().body(token);
    }
}
