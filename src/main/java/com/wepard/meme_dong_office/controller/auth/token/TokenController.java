package com.wepard.meme_dong_office.controller.auth.token;

import com.wepard.meme_dong_office.dto.token.request.TokenRequestDTO;
import com.wepard.meme_dong_office.dto.token.response.TokenResponseDTO;
import com.wepard.meme_dong_office.service.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class TokenController {

    private final TokenService tokenService;

    @Autowired
    public TokenController(
            TokenService tokenService
    ){
        this.tokenService = tokenService;
    }

    @PostMapping("/token")
    public ResponseEntity<TokenResponseDTO> getToken(@RequestBody TokenRequestDTO tokenRequestDTO){
        return ResponseEntity
                .ok()
                .body(tokenService.signIn(tokenRequestDTO));
    }
}
