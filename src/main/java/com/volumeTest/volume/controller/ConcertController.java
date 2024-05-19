package com.volumeTest.volume.controller;

import com.volumeTest.volume.dto.ConcertRequestDto;
import com.volumeTest.volume.dto.ConcertResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/concerts")
public class ConcertController {

    @GetMapping
    public List<ConcertResponseDto> showConcertList() {

    }
    @PostMapping("/concert")
    public ConcertRequestDto createConcert() {

    }

    @GetMapping("/{id}")
    public ConcertResponseDto showConcert(@PathVariable("id") Long id){

    }
    @PutMapping("/{id}")
    public ConcertResponseDto editConcert() {

    }

    @DeleteMapping("/{id}")
    public void deleteConcert() {

    }

}
