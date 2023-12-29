package com.wepard.meme_dong_office.exception.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ExceptionCode {
    //400~
    INVALID_FILE(HttpStatus.NOT_ACCEPTABLE, "Invalid file."),
    DATA_NOT_EXIST(HttpStatus.NOT_ACCEPTABLE, "Data not exist."),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "Request is missing or empty."),
    INVALID_ACCESS(HttpStatus.FORBIDDEN, "Cannot access this."),
    TOKEN_NOT_VALID(HttpStatus.UNAUTHORIZED, "Token is not valid."),
    LOGIN_FAILED(HttpStatus.NOT_ACCEPTABLE, "User information is not valid."),
    EMAIL_DUPLICATE(HttpStatus.CONFLICT, "User email is already exist."),

    //500~
    FAILED_TO_DELETE_DATA(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete data."),
    FAILED_TO_FIND_STUDENTS(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to find students."),
    FAILED_TO_FIND_STUDENTS_LIST(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to find students list."),
    FAILED_TO_FIND_USER(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to find user."),
    FAILED_TO_CREATE_STUDENTS_LIST(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create students list."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Server Error");

    private final HttpStatus status;
    private final String message;
}
