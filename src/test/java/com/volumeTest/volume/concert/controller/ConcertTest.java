package com.volumeTest.volume.concert.controller;

import com.volumeTest.volume.concert.dto.ConcertRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@Slf4j
@SpringBootTest
public class ConcertTest {

    @Autowired
    ConcertController concertController;
    @BeforeEach
    void createData() {
        ConcertRequestDto dto1 = ConcertRequestDto.builder()
                .concertName("steve")
                .concertDate(LocalDate.parse("2020-08-07"))
                .build();
        ConcertRequestDto dto2 = ConcertRequestDto.builder()
                .concertName("julie")
                .concertDate(LocalDate.parse("2020-08-20"))
                .build();
        concertController.createConcert(dto1);
        concertController.createConcert(dto2);
        log.info("아ㅓ니아ㅓ니 = {}", dto1);
    }

    @AfterEach
    void deleteData() {
        concertController.deleteConcert(1L);
        concertController.deleteConcert(2L);
    }
    @Test
    void showDate(){
        Assertions.assertThat(concertController.showConcert(1L).getConcertName()).isEqualTo("steve");
    }

    @Test
    void showDateList() {
        Assertions.assertThat(concertController.showConcertList().size()).isEqualTo(2);
        log.info(String.valueOf(concertController.showConcertList().get(0).getConcertId()));
    }

    @Test
    void updateDate() {
        ConcertRequestDto updateConcert = ConcertRequestDto.builder()
                .concertName("stove")
                .concertDate(LocalDate.parse("2020-08-20"))
                .build();
        Assertions.assertThat(concertController.updateConcert(1L, updateConcert).getConcertName()).isEqualTo("stove");
    }

    @Test
    void delete() {
        concertController.deleteConcert(1L);
        Assertions.assertThat(concertController.showConcertList().size()).isEqualTo(1);
    }

}
