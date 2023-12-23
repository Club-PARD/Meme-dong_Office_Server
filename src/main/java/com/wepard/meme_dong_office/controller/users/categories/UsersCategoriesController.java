package com.wepard.meme_dong_office.controller.users.categories;

import com.wepard.meme_dong_office.DTO.categories.response.CategoriesResponseDTO;
import com.wepard.meme_dong_office.DTO.usersCategories.response.UsersCategoriesResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class UsersCategoriesController {

    @GetMapping("/users/{userId}/categories")
    public ResponseEntity<?> getUsersCategories(@PathVariable Long userId){

        Map<String, Integer> category = new HashMap<>();
        category.put("카테고리 명",5);
        category.put("동아리",1);

        UsersCategoriesResponseDTO categories = new UsersCategoriesResponseDTO();
        categories.setCategories(category);

        return ResponseEntity.ok().body(categories);
    }
}
