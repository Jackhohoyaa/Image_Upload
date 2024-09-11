package com.line.line_dev_ec_pretest.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AppException extends RuntimeException{

    private HttpStatus httpStatus;

    public AppException(String msg, HttpStatus status) {
        super(msg);
        this.httpStatus = status;
    }

}
