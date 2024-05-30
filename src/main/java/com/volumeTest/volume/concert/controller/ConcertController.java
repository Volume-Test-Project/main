package com.volumeTest.volume.concert.controller;

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
    public List<ConcertResponseDto> showConcertList() {
        return concertService.findAll();
    }
    @PostMapping("/concert")
    public ConcertResponseDto createConcert(@RequestBody ConcertRequestDto concertRequestDto) {
        return concertService.createConcert(concertRequestDto);
    }

    @GetMapping("/{id}")
    public ConcertResponseDto showConcert(@PathVariable("id") Long id){
        return concertService.findById(id);
    }
    @PutMapping("/{id}")
    public ConcertResponseDto updateConcert(@PathVariable("id") Long id, @RequestBody ConcertRequestDto concertRequestDto) {
        return concertService.updateConcert(id, concertRequestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteConcert(@PathVariable("id") Long id) {
        concertService.deleteConcert(id);
    }

}