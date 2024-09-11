package com.line.line_dev_ec_pretest.handlers;

import com.line.line_dev_ec_pretest.utils.AppException;
import com.line.line_dev_ec_pretest.vo.ResponseMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

@ControllerAdvice
@Slf4j
public class ExceptionHandlers {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Object> handleMissingRequestParamException(MissingServletRequestParameterException e) {
        String message = e.getParameterName() + " could not be null";
        log.error("MissingRequestParamException: " + message,e);
        return ResponseEntity.badRequest().body(new ResponseMsg("Failure", message));
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    public ResponseEntity<Object> handleMissingServletRequestPartException(MissingServletRequestPartException e) {
        log.error("MissingServletRequestPartException: " + e.getMessage(),e);
        return ResponseEntity.badRequest()
                .body(new ResponseMsg("Failure", "Missing Servlet Request Part Exception: " + e.getMessage()));
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<Object> handleMaxUploadSizeExceededException(Exception e){
        log.error("MaxUploadSizeExceededException: " + e.getMessage(),e);
        return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE)
                .body(new ResponseMsg("Failure","Max Upload Size Exceeded Exception: " + e.getMessage()));
    }

    @ExceptionHandler(value = {AppException.class})
    public ResponseEntity<Object> handleAppException(AppException e) {
        log.error("AppException: " + e.getMessage(), e);
        return ResponseEntity.status(e.getHttpStatus())
                .body(new ResponseMsg("Failure", "App Error: " + e.getMessage()));
    }


    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleException(Exception e){
        log.error("Exception : " + e.getMessage(),e);
        return ResponseEntity.internalServerError()
                .body(new ResponseMsg("Failure","Server Error: " + e.getMessage()));
    }

}