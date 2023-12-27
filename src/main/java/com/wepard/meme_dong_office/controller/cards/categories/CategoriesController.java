package com.wepard.meme_dong_office.controller.cards.categories;

import com.wepard.meme_dong_office.dto.cards.response.CardsResponseDTO;
import com.wepard.meme_dong_office.dto.categories.request.CategoriesRequestDTO;
import com.wepard.meme_dong_office.dto.categories.response.CategoriesResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class CategoriesController {

    @PostMapping("/cards/{cardId}/categories")
    public ResponseEntity<?> createCategories(
            @PathVariable Long cardId,
            @RequestBody CategoriesRequestDTO categoriesRequestDTO
            ){

        CategoriesResponseDTO categories = new CategoriesResponseDTO();
        categories.setCategory(categoriesRequestDTO.getCategory());

        CardsResponseDTO card = new CardsResponseDTO();
        card.setId(1L);
        card.setName("사람 이름");
        card.setOneLiner("한 줄 설명");
        card.setImageURL("https://example.url");
        card.setCreatedAt(LocalDateTime.now());
        card.setCorrectCount(0);
        card.setIncorrectCount(0);
        card.setCategories(categories);

        return ResponseEntity.ok().body(card);
    }

    @GetMapping("/cards/{cardId}/categories")
    public ResponseEntity<?> getCategories(@PathVariable Long cardId){

        Map<String, String> category = new HashMap<>();
        category.put("카테고리 명","카테고리 값");
        category.put("동아리","PARD");

        CategoriesResponseDTO categories = new CategoriesResponseDTO();
        categories.setCategory(category);

        return ResponseEntity.ok().body(categories);
    }

    @PatchMapping("/cards/{cardId}/categories")
    public ResponseEntity<?> updateCategories(
            @PathVariable Long cardId,
            @RequestBody CategoriesRequestDTO categoriesRequestDTO
    ){

        CategoriesResponseDTO categories = new CategoriesResponseDTO();
        categories.setCategory(categoriesRequestDTO.getCategory());

        CardsResponseDTO card = new CardsResponseDTO();
        card.setId(1L);
        card.setName("사람 이름");
        card.setOneLiner("한 줄 설명");
        card.setImageURL("https://example.url");
        card.setCreatedAt(LocalDateTime.now());
        card.setCorrectCount(0);
        card.setIncorrectCount(0);
        card.setCategories(categories);

        return ResponseEntity.ok().body(card);
    }

    @DeleteMapping("/cards/{cardId}/categories")
    public ResponseEntity<?> deleteCategories(
            @PathVariable Long cardId,
            @RequestBody List<String> categoryKeys
    ){

        Map<String, String> category = new HashMap<>();
        category.put("카테고리 명","카테고리 값");

        CategoriesResponseDTO categories = new CategoriesResponseDTO();
        categories.setCategory(category);

        CardsResponseDTO card = new CardsResponseDTO();
        card.setId(1L);
        card.setName("사람 이름");
        card.setOneLiner("한 줄 설명");
        card.setImageURL("https://example.url");
        card.setCreatedAt(LocalDateTime.now());
        card.setCorrectCount(0);
        card.setIncorrectCount(0);
        card.setCategories(categories);

        return ResponseEntity.ok().body(card);
    }
}
