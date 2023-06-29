package com.example.contents;

import com.example.contents.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

@Slf4j
@RestController
public  class FileController {
    @PostMapping(value = "/multipart", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseDto multipart(
        @RequestParam("name") String name,
        @RequestParam("file") MultipartFile multipartFile
    ) throws IOException {
        log.info(name);
        log.info(multipartFile.toString());
        // 파일 저장
        Files.createDirectories(Path.of("media"));

        // timestamp 생성
        LocalDateTime now = LocalDateTime.now();
        log.info(now.toString());

        String filename = now.toString().replace(":", "");
        Path uploadTo = Path.of(String.format("media/%s.png", filename));
        multipartFile.transferTo(uploadTo);

        // 파일 내용을 바이트로 변환 후 저장
        File file = new File("./media/filename.png");
        
        try (OutputStream outputStream = new FileOutputStream(file)){
           byte[] fileBytes = multipartFile.getBytes();

           outputStream.write(fileBytes);
        }

        ResponseDto response = new ResponseDto();
        response.setMessage("success");

        return response;
    }
}
