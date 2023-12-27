package com.wepard.meme_dong_office.exception.handler;

import com.wepard.meme_dong_office.dto.exception.response.ExceptionResponseDTO;
import com.wepard.meme_dong_office.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class)
    protected final ResponseEntity handleCustomExceptions(CustomException ex){
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(LocalDateTime.now(), ex.getExceptionCode().getStatus(), ex.getExceptionCode().getMessage());
        return new ResponseEntity(exceptionResponseDTO, ex.getExceptionCode().getStatus());
    }

    @ExceptionHandler({ Exception.class })
    protected final ResponseEntity handleAllException(Exception ex){
        log.error("INTERNAL SERVER ERROR:{}", ex.getMessage(), ex);
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return new ResponseEntity(exceptionResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
