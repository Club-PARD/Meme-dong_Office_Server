package com.wepard.meme_dong_office.DTO.categories.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class CategoriesRequestDTO {
    private Map<String, String> category;
}
