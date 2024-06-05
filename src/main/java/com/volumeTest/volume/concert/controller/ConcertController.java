package com.volumeTest.volume.concert.controller;

import static com.volumeTest.volume.common.util.ApiResponseUtil.success;

import com.volumeTest.volume.common.util.ApiResponseUtil;
import com.volumeTest.volume.common.util.ApiResponseUtil.ApiResult;
import com.volumeTest.volume.concert.dto.ConcertRequestDto;
import com.volumeTest.volume.concert.dto.ConcertResponseDto;
import com.volumeTest.volume.concert.service.ConcertService;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/concerts")
public class ConcertController {

    @Autowired
    ConcertService concertService;

    @GetMapping
    public ResponseEntity<Object> showConcertList() {
        return new ResponseEntity<>(success(concertService.findAll()), new HttpHeaders(), HttpStatus.OK);
    }
    @PostMapping("/concert")
    public ResponseEntity<Object> createConcert(@RequestBody ConcertRequestDto concertRequestDto) {
        return new ResponseEntity<>(success(concertService.createConcert(concertRequestDto)), new HttpHeaders(), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> showConcert(@PathVariable("id") Long id){
        return new ResponseEntity<>(success(concertService.findById(id)), new HttpHeaders(), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateConcert(@PathVariable("id") Long id, @RequestBody ConcertRequestDto concertRequestDto) {
        return new ResponseEntity<>(success(concertService.updateConcert(id, concertRequestDto)), new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteConcert(@PathVariable("id") Long id) {
        concertService.deleteConcert(id);
    }

}
