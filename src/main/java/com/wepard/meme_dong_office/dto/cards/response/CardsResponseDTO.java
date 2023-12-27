package com.wepard.meme_dong_office.dto.cards.response;

import com.wepard.meme_dong_office.dto.categories.response.CategoriesResponseDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CardsResponseDTO {
    private Long id;
    private String name;
    private String oneLiner;
    private String imageURL;
    private LocalDateTime createdAt;
    private Integer correctCount;
    private Integer incorrectCount;
    private CategoriesResponseDTO categories;
}
