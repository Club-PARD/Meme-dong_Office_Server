package com.wepard.meme_dong_office.controller.students;

import com.wepard.meme_dong_office.dto.students.request.StudentsCreateUpdateRequestDTO;
import com.wepard.meme_dong_office.dto.students.response.StudentsResponseDTO;
import com.wepard.meme_dong_office.security.TokenProvider;
import com.wepard.meme_dong_office.service.students.StudentsService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
public class StudentsController {

    private final TokenProvider tokenProvider;
    private final StudentsService studentsService;

    @Autowired
    public StudentsController(
            final TokenProvider tokenProvider,
            final StudentsService studentsService
    ){
        this.tokenProvider = tokenProvider;
        this.studentsService = studentsService;
    }

    @PostMapping("/students")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "학생 정보 등록 성공"
            )
    })
    public ResponseEntity<?> createStudents(
            @RequestHeader(value = "Authorization") final String token,
            @RequestBody final StudentsCreateUpdateRequestDTO studentsCreateUpdateRequestDTO
    ){

        final Long userId = Long.parseLong(
                tokenProvider.validate(token.substring(7))
        );

        final Long savedStudentsId = studentsService.createStudents(
                studentsCreateUpdateRequestDTO,
                userId
        );

        final URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/students/{id}")
                .buildAndExpand(savedStudentsId)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/students/{id}")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "학생 정보 조회 성공",
                    content = @Content(schema = @Schema(implementation = StudentsResponseDTO.class))
            )
    })
    public ResponseEntity<?> getStudents(
            @PathVariable final Long id,
            @RequestHeader(value = "Authorization") final String token
    ){

        final Long userId = Long.parseLong(
                tokenProvider.validate(token.substring(7))
        );

        return ResponseEntity.ok().body(
                studentsService.getStudent(id, userId)
        );
    }

    @PatchMapping("/students/{id}")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "학생 정보 업데이트 성공",
                    content = @Content(schema = @Schema(implementation = StudentsResponseDTO.class))
            )
    })
    public ResponseEntity<?> updateStudents(
            @PathVariable final Long id,
            @RequestHeader(value = "Authorization") final String token,
            @RequestBody final StudentsCreateUpdateRequestDTO studentsCreateUpdateRequestDTO
    ){

        final Long userId = Long.parseLong(
                tokenProvider.validate(token.substring(7))
        );

        return ResponseEntity.ok().body(
                studentsService.updateStudents(studentsCreateUpdateRequestDTO, id, userId)
        );
    }

    @DeleteMapping("/students/{id}")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "학생 정보 삭제 성공"
            )
    })
    public ResponseEntity<?> deleteStudents(
            @PathVariable final Long id,
            @RequestHeader(value = "Authorization") final String token
    ){

        final Long userId = Long.parseLong(
                tokenProvider.validate(token.substring(7))
        );

        studentsService.deleteStudents(id, userId);

        return ResponseEntity.noContent().build();
    }
}
