package com.line.line_dev_ec_pretest.controller;

import com.line.line_dev_ec_pretest.service.FileService;
import com.line.line_dev_ec_pretest.vo.ResponseMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    FileService fileService;

    @PostMapping()
    public ResponseEntity<Object> uploadFile(@RequestParam MultipartFile file) throws IOException {
        ResponseMsg result = fileService.uploadFile(file);
        return ResponseEntity.ok().body(result);
    }
}
