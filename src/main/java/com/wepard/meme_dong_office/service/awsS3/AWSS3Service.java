package com.wepard.meme_dong_office.service.awsS3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.wepard.meme_dong_office.exception.CustomException;
import com.wepard.meme_dong_office.exception.constants.ExceptionCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class AWSS3Service {

    private final AmazonS3 amazonS3;

    @Autowired
    public AWSS3Service(
            final AmazonS3 amazonS3
    ){
        this.amazonS3 = amazonS3;
    }

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${aws.cloudfront.url}")
    private String CloudFrontURL;

    public String uploadImages(
            MultipartFile multipartFile
    ){
        final List<String> ALLOWED_EXTENSIONS = Arrays.asList("jpg", "jpeg", "png", "svg", "heic");
        final String extension = StringUtils.getFilenameExtension(multipartFile.getOriginalFilename());
        final String fileName = UUID.randomUUID().toString().replaceAll("-","")+"."+extension;

        //이미지 파일이 아닌 파일 예외처리
        if(!ALLOWED_EXTENSIONS.contains(extension.toLowerCase())){
            throw new CustomException(ExceptionCode.INVALID_FILE);
        }

        ObjectMetadata objectMetadata = new ObjectMetadata();
        try {
            objectMetadata.setContentLength(multipartFile.getInputStream().available());
            amazonS3.putObject(bucket, fileName, multipartFile.getInputStream(), objectMetadata);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return CloudFrontURL+"/"+fileName;
    }
}
