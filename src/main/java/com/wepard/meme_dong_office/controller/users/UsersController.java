package com.wepard.meme_dong_office.controller.users;

import com.wepard.meme_dong_office.dto.users.request.UsersRequestDTO;
import com.wepard.meme_dong_office.dto.users.response.UsersResponseDTO;
import com.wepard.meme_dong_office.exception.CustomException;
import com.wepard.meme_dong_office.exception.constants.ExceptionCode;
import com.wepard.meme_dong_office.security.TokenProvider;
import com.wepard.meme_dong_office.service.users.UsersService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UsersController {

    private final TokenProvider tokenProvider;
    private final UsersService usersService;

    @GetMapping("/users/{id}")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "유저 정보 조회 성공",
                    content = @Content(schema = @Schema(implementation = UsersResponseDTO.class))
            )
    })
    public ResponseEntity<?> getUsers(
            @PathVariable final Long id,
            @RequestHeader(value = "Authorization") final String token
    ){

        final Long userId = Long.parseLong(
                tokenProvider.validate(token.substring(7))
        );

        //다른 사람의 정보 조회 막기
        if(!userId.equals(id)){
            throw new CustomException(ExceptionCode.INVALID_ACCESS);
        }

        return ResponseEntity.ok().body(
                usersService.getUsers(userId)
        );
    }

    @PatchMapping("/users/{id}")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "유저 정보 업데이트 성공",
                    content = @Content(schema = @Schema(implementation = UsersResponseDTO.class))
            )
    })
    public ResponseEntity<?> updateUsers(
            @PathVariable final Long id,
            @RequestHeader(value = "Authorization") final String token,
            @RequestBody final UsersRequestDTO usersRequestDTO
            ){

        final Long userId = Long.parseLong(
                tokenProvider.validate(token.substring(7))
        );

        //다른 사람의 정보 조회 막기
        if(!userId.equals(id)){
            throw new CustomException(ExceptionCode.INVALID_ACCESS);
        }

        return ResponseEntity.ok().body(
                usersService.updateUsers(
                        usersRequestDTO,
                        userId
                )
        );
    }

    @DeleteMapping("/users/{id}")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "유저 정보 삭제 성공"
            )
    })
    public ResponseEntity<?> deleteUsers(
            @PathVariable final Long id,
            @RequestHeader(value = "Authorization") final String token
    ){

        final Long userId = Long.parseLong(
                tokenProvider.validate(token.substring(7))
        );

        //다른 사람의 정보 조회 막기
        if(!userId.equals(id)){
            throw new CustomException(ExceptionCode.INVALID_ACCESS);
        }

        usersService.deleteUsers(userId);

        return ResponseEntity.noContent().build();
    }
}
