package com.wepard.meme_dong_office.dto.exception.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ExceptionResponseDTO {
    private LocalDateTime time;
    private HttpStatus status;
    private String message;
}
