package com.wepard.meme_dong_office.controller.auth.token;

import com.wepard.meme_dong_office.dto.token.request.TokenRequestDTO;
import com.wepard.meme_dong_office.dto.token.response.TokenResponseDTO;
import com.wepard.meme_dong_office.service.token.TokenService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class TokenController {

    private final TokenService tokenService;

    @PostMapping("/token")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "토큰 발급 성공",
                    content = @Content(schema = @Schema(implementation = TokenResponseDTO.class))
            )
    })
    public ResponseEntity<?> getToken(
            @RequestBody TokenRequestDTO tokenRequestDTO
    ){
        return ResponseEntity
                .ok()
                .body(tokenService.signIn(tokenRequestDTO));
    }
}
