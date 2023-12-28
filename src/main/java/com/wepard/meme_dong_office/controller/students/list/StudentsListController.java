package com.wepard.meme_dong_office.controller.students.list;

import com.wepard.meme_dong_office.dto.students.list.request.StudentsListRequestDTO;
import com.wepard.meme_dong_office.security.TokenProvider;
import com.wepard.meme_dong_office.service.students.list.StudentsListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/students")
public class StudentsListController {

    private final StudentsListService studentsListService;
    private final TokenProvider tokenProvider;

    @Autowired
    public StudentsListController(
            StudentsListService studentsListService,
            TokenProvider tokenProvider
    ){
        this.studentsListService = studentsListService;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/list")
    public ResponseEntity<?> createStudentsList(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody StudentsListRequestDTO studentsListRequestDTO
    ){

        final Long userId = Long.parseLong(
                tokenProvider.validate(token.substring(7))
        );


        final Long savedStudentsListId = studentsListService.createStudentsList(
                studentsListRequestDTO,
                userId
        );

        final URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/students/list/{id}")
                .buildAndExpand(savedStudentsListId)
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
