package com.wepard.meme_dong_office.controller.auth.users;

import com.wepard.meme_dong_office.dto.users.request.UsersRequestDTO;
import com.wepard.meme_dong_office.dto.users.response.UsersResponseDTO;
import com.wepard.meme_dong_office.service.users.UsersService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/auth")
public class UsersCreateController {

    private final UsersService usersService;

    @Autowired
    public UsersCreateController(
            UsersService usersService
    ){
        this.usersService = usersService;
    }

    @PostMapping("/users")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "유저 생성 성공"
            )
    })
    public ResponseEntity<?> createUsers(
            @RequestBody final UsersRequestDTO usersRequestDTO
    ){
        final Long savedUserId = usersService.signUp(usersRequestDTO);

        final URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/user/{id}")
                .buildAndExpand(savedUserId)
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
