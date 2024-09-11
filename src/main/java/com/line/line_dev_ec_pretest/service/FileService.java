package com.line.line_dev_ec_pretest.service;

import com.line.line_dev_ec_pretest.utils.AppException;
import com.line.line_dev_ec_pretest.vo.ResponseMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class FileService {

    @Value("${file.path}")
    private  String filePath;

    public ResponseMsg uploadFile(MultipartFile file) throws IOException {

        if (file.isEmpty()) {
            throw new AppException("No file is provided in the request", HttpStatus.BAD_REQUEST);
        }

        // check file path is existing or not
        Path directoryPath = Paths.get(filePath);
        if (!Files.exists(directoryPath)) {
            // if file path is not exist , create a new directory
            Files.createDirectories(directoryPath);
        }

        //check file extension
        String fileExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1).toLowerCase();
        if (!Arrays.asList("pdf", "png", "jpg", "gif").contains(fileExtension)) {
            throw new AppException("The file type is not allowed", HttpStatus.BAD_REQUEST);
        }

        String fileName = file.getOriginalFilename();
        Path directFilePath = directoryPath.resolve(fileName);

        file.transferTo(directFilePath);

        Map<String, String> map = new HashMap<>();
        map.put("filePath", directFilePath.toString());

        ResponseMsg responseMsg = ResponseMsg.builder().status("Success").message("File Uploaded Successfully").data(map).build();

        log.info("RESULT: status:{},message:{}",responseMsg.getStatus(),responseMsg.getMessage());
        return responseMsg;
    }
}
