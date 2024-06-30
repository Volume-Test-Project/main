package com.volumeTest.volume.common.exception;

import static com.volumeTest.volume.common.util.ApiResponseUtil.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestControllerAdvice
@Slf4j
public class VolumeTestExceptionHandler {

    @ExceptionHandler(VolumeTestException.class)
    public ResponseEntity<Object> handleVolumeTestException(VolumeTestException e) {
        //로그 로직 필요
        log.info("예외 발생 : {}", e.getMessage());
        return new ResponseEntity(error(e.getMessage()), new HttpHeaders(), e.getStatus());
    }
}
