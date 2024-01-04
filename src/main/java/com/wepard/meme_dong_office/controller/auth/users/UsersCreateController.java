package com.wepard.meme_dong_office.controller.auth.users;

import com.wepard.meme_dong_office.dto.token.response.TokenResponseDTO;
import com.wepard.meme_dong_office.dto.users.request.UsersRequestDTO;
import com.wepard.meme_dong_office.service.users.UsersService;
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
public class UsersCreateController {

    private final UsersService usersService;

    @PostMapping("/users")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "유저 생성 성공",
                    content = @Content(schema = @Schema(implementation = TokenResponseDTO.class))
            )
    })
    public ResponseEntity<?> createUsers(
            @RequestBody final UsersRequestDTO usersRequestDTO
    ){
        return ResponseEntity.ok().body(
                usersService.signUp(usersRequestDTO)
        );
    }
}
