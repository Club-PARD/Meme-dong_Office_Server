package com.wepard.meme_dong_office.exception.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ExceptionCode {
    //400~
    TOKEN_NOT_VALID(HttpStatus.UNAUTHORIZED, "Token is not valid."),
    LOGIN_FAILED(HttpStatus.NOT_ACCEPTABLE, "User information is not valid."),
    EMAIL_DUPLICATE(HttpStatus.CONFLICT, "User email is already exist."),

    //500~
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Server Error");

    private final HttpStatus status;
    private final String message;
}
