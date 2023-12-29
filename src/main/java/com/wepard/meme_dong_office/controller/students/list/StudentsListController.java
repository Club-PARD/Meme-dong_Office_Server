package com.wepard.meme_dong_office.controller.students.list;

import com.wepard.meme_dong_office.dto.students.list.request.StudentsListRequestDTO;
import com.wepard.meme_dong_office.dto.students.list.request.StudentsListUpdateRequestDTO;
import com.wepard.meme_dong_office.dto.students.list.response.StudentsListResponseDTO;
import com.wepard.meme_dong_office.dto.users.response.UsersResponseDTO;
import com.wepard.meme_dong_office.security.TokenProvider;
import com.wepard.meme_dong_office.service.students.list.StudentsListService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

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
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "학급 정보 생성 성공"
            )
    })
    public ResponseEntity<?> createStudentsList(
            @RequestHeader(value = "Authorization") final String token,
            @RequestBody final StudentsListRequestDTO studentsListRequestDTO
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

    @GetMapping("/list/{id}")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "학급 정보 조회 성공",
                    content = @Content(schema = @Schema(implementation = StudentsListResponseDTO.class))
            )
    })
    public ResponseEntity<?> getStudentsList(
            @PathVariable final Long id,
            @RequestHeader(value = "Authorization") final String token
    ){

        final Long userId = Long.parseLong(
                tokenProvider.validate(token.substring(7))
        );

        return ResponseEntity.ok().body(
                studentsListService.getStudentsList(id, userId)
        );
    }

    @PatchMapping("/list/{id}")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "학급 정보 수정 성공",
                    content = @Content(schema = @Schema(implementation = StudentsListResponseDTO.class))
            )
    })
    public ResponseEntity<?> updateStudentsList(
            @PathVariable final Long id,
            @RequestHeader(value = "Authorization") final String token,
            @RequestBody final StudentsListUpdateRequestDTO studentsListUpdateRequestDTO
    ){

        final Long userId = Long.parseLong(
                tokenProvider.validate(token.substring(7))
        );

        return ResponseEntity.ok().body(
          studentsListService.updateStudentsList(studentsListUpdateRequestDTO, id, userId)
        );
    }

    @DeleteMapping("/list/{id}")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "학급 정보 삭제 성공"
            )
    })
    public ResponseEntity<?> deleteStudentsList(
            @PathVariable final Long id,
            @RequestHeader(value = "Authorization") final String token
    ){

        final Long userId = Long.parseLong(
                tokenProvider.validate(token.substring(7))
        );

        studentsListService.deleteStudentsList(id, userId);

        return ResponseEntity.noContent().build();
    }
}
