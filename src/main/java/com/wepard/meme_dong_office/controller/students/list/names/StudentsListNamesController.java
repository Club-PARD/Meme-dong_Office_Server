package com.wepard.meme_dong_office.controller.students.list.names;

import com.wepard.meme_dong_office.dto.students.list.names.response.StudentsListNamesResponseDTO;
import com.wepard.meme_dong_office.security.TokenProvider;
import com.wepard.meme_dong_office.service.students.list.names.StudentsListNamesService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/students/list")
public class StudentsListNamesController {

    private final StudentsListNamesService studentsListNamesService;
    private final TokenProvider tokenProvider;

    @Autowired
    public StudentsListNamesController(
            StudentsListNamesService studentsListNamesService,
            TokenProvider tokenProvider
    ){
        this.studentsListNamesService = studentsListNamesService;
        this.tokenProvider = tokenProvider;
    }

    @GetMapping("/names/{id}")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "학급 정보 이름 조회 성공",
                    content = @Content(schema = @Schema(implementation = StudentsListNamesResponseDTO.class))
            )
    })
    public ResponseEntity<?> getStudentsListNames(
            @PathVariable final Long id,
            @RequestHeader(value = "Authorization") final String token
    ){

        final Long userId = Long.parseLong(
                tokenProvider.validate(token.substring(7))
        );

        return ResponseEntity.ok().body(studentsListNamesService.getStudentsListNames(id, userId));
    }
}
