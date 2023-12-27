package com.wepard.meme_dong_office.exception;

import com.wepard.meme_dong_office.exception.constants.ExceptionCode;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{

    private final ExceptionCode exceptionCode;

    public CustomException(ExceptionCode exceptionCode){
        this.exceptionCode = exceptionCode;
    }
}
