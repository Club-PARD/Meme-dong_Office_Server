package com.wepard.meme_dong_office.controller.users;

import com.wepard.meme_dong_office.dto.users.response.UsersResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UsersController {

    @GetMapping("/users/{userId}")
    public ResponseEntity<?> getUsers(@PathVariable Long userId){
        UsersResponseDTO user = UsersResponseDTO.builder()
                .id(1L)
                .name("name")
                .email("email@email.com")
                .build();
        return ResponseEntity.ok().body(user);
    }

    @PatchMapping("/users/{userId}")
    public ResponseEntity<?> updateUsers(@PathVariable Long userId){
        UsersResponseDTO user = UsersResponseDTO.builder()
                .id(1L)
                .name("name")
                .email("email@email.com")
                .build();
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> deleteUsers(@PathVariable Long userId){
        return ResponseEntity.noContent().build();
    }
}
