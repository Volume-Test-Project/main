package com.volumeTest.volume.common.exception;

import static com.volumeTest.volume.common.util.ApiResponseUtil.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestControllerAdvice
public class VolumeTestExceptionHandler {

    @ExceptionHandler(VolumeTestException.class)
    public ResponseEntity<Object> handleVolumeTestException(VolumeTestException e) {
        //로그 로직 필요
        return new ResponseEntity(error(e.getMessage()), new HttpHeaders(), e.getStatus());
    }
}
