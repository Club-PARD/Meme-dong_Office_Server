package com.wepard.meme_dong_office.controller.students.images;

import com.wepard.meme_dong_office.security.TokenProvider;
import com.wepard.meme_dong_office.service.students.images.StudentsImagesService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
public class StudentsImagesController {

    private final TokenProvider tokenProvider;
    private final StudentsImagesService studentsImagesService;

    @Autowired
    public StudentsImagesController(
            final TokenProvider tokenProvider,
            final StudentsImagesService studentsImagesService
    ){
        this.tokenProvider = tokenProvider;
        this.studentsImagesService = studentsImagesService;
    }

    @PostMapping(value = "/students/{id}/images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "이미지 업로드 성공"
            )
    })
    public ResponseEntity<?> createStudentsImage(
            @PathVariable final Long id,
            @RequestHeader(value = "Authorization") final String token,
            @RequestBody final MultipartFile multipartFile
    ){

        final Long userId = Long.parseLong(
                tokenProvider.validate(token.substring(7))
        );

        final String imageURL = studentsImagesService.uploadStudentsImages(multipartFile, id, userId);

        URI location = ServletUriComponentsBuilder
                .fromHttpUrl(imageURL)
                .build()
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/students/{id}/images")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "이미지 삭제 성공"
            )
    })
    public ResponseEntity<?> deleteStudentsImages(
            @PathVariable final Long id,
            @RequestHeader(value = "Authorization") final String token
    ){

        final Long userId = Long.parseLong(
                tokenProvider.validate(token.substring(7))
        );

        studentsImagesService.deleteStudentsImages(id, userId);

        return ResponseEntity.noContent().build();
    }
}
