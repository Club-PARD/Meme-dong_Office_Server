package com.wepard.meme_dong_office.controller.auth.users;

import com.wepard.meme_dong_office.dto.users.request.UsersRequestDTO;
import com.wepard.meme_dong_office.service.users.UsersService;
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
    public ResponseEntity createUsers(
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
