package com.volumeTest.volume.concert.controller;

import com.volumeTest.volume.common.util.ApiResponseUtil;
import com.volumeTest.volume.common.util.ApiResponseUtil.ApiResult;
import com.volumeTest.volume.concert.dto.ConcertRequestDto;
import com.volumeTest.volume.concert.dto.ConcertResponseDto;
import com.volumeTest.volume.concert.service.ConcertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/concerts")
public class ConcertController {

    @Autowired
    ConcertService concertService;

    @GetMapping
    public ApiResult<List<ConcertResponseDto>> showConcertList() {
        return ApiResponseUtil.success(concertService.findAll());
    }
    @PostMapping("/concert")
    public ApiResult<ConcertResponseDto> createConcert(@RequestBody ConcertRequestDto concertRequestDto) {
        return ApiResponseUtil.success(concertService.createConcert(concertRequestDto));
    }

    @GetMapping("/{id}")
    public ApiResult<ConcertResponseDto> showConcert(@PathVariable("id") Long id){
        return ApiResponseUtil.success(concertService.findById(id));
    }
    @PutMapping("/{id}")
    public ApiResult<ConcertResponseDto> updateConcert(@PathVariable("id") Long id, @RequestBody ConcertRequestDto concertRequestDto) {
        return ApiResponseUtil.success(concertService.updateConcert(id, concertRequestDto));
    }

    @DeleteMapping("/{id}")
    public void deleteConcert(@PathVariable("id") Long id) {
        concertService.deleteConcert(id);
    }

}
