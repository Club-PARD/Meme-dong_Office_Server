package com.wepard.meme_dong_office.controller.cards;

import com.wepard.meme_dong_office.dto.cards.request.CardsRequestDTO;
import com.wepard.meme_dong_office.dto.cards.response.CardsResponseDTO;
import com.wepard.meme_dong_office.dto.categories.response.CategoriesResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class CardsController {

    @PostMapping("/cards")
    public ResponseEntity<?> createCards(@RequestBody CardsRequestDTO cardsRequestDTO){

        CardsResponseDTO card = new CardsResponseDTO();
        card.setId(1L);
        card.setName("사람 이름");
        card.setOneLiner("한 줄 설명");
        card.setImageURL("https://example.url");
        card.setCreatedAt(LocalDateTime.now());
        card.setCorrectCount(0);
        card.setIncorrectCount(0);
        card.setCategories(new CategoriesResponseDTO());

        return ResponseEntity.ok().body(card);
    }

    @GetMapping("/cards/{cardId}")
    public ResponseEntity<?> getCards(@PathVariable Long cardId){

        Map<String, String> category = new HashMap<>();
        category.put("카테고리 명","카테고리 값");
        category.put("동아리","PARD");

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

    @GetMapping("/cards")
    public ResponseEntity<?> searchCards(
            @RequestParam Integer searchBy,
            @RequestParam Integer sortBy
    ){
        List<CardsResponseDTO> cardList = new ArrayList<>();

        Map<String, String> category = new HashMap<>();
        category.put("카테고리 명","카테고리 값");
        category.put("동아리","PARD");

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

        CardsResponseDTO card2 = new CardsResponseDTO();
        card2.setId(2L);
        card2.setName("사람 이름");
        card2.setOneLiner("한 줄 설명");
        card2.setImageURL("https://example.url");
        card2.setCreatedAt(LocalDateTime.now());
        card2.setCorrectCount(0);
        card2.setIncorrectCount(0);
        card2.setCategories(categories);

        cardList.add(card);
        cardList.add(card2);

        return ResponseEntity.ok().body(cardList);
    }

    @PatchMapping("/cards/{cardId}")
    public ResponseEntity<?> updateCards(
            @PathVariable Long cardId,
            @RequestBody CardsRequestDTO cardsRequestDTO
    ){
        Map<String, String> category = new HashMap<>();
        category.put("카테고리 명","카테고리 값");
        category.put("동아리","PARD");

        CategoriesResponseDTO categories = new CategoriesResponseDTO();
        categories.setCategory(category);

        CardsResponseDTO card = new CardsResponseDTO();
        card.setId(1L);
        card.setName("바뀐 사람 이름");
        card.setOneLiner("바뀐 한 줄 설명");
        card.setImageURL("https://example.url");
        card.setCreatedAt(LocalDateTime.now());
        card.setCorrectCount(0);
        card.setIncorrectCount(0);
        card.setCategories(categories);

        return ResponseEntity.ok().body(card);
    }

    @DeleteMapping("/cards/{cardId}")
    public ResponseEntity<?> deleteCards(@PathVariable Long cardId){
        return ResponseEntity.noContent().build();
    }
}
