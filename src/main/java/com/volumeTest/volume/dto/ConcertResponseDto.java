package com.volumeTest.volume.dto;

import com.volumeTest.volume.entity.ConcertEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Builder
public class ConcertResponseDto {

    @NotNull
    private Long concertId;

    @NotBlank
    private String concertName;

    @NotBlank
    private LocalDate concertDate;

    public ConcertResponseDto(ConcertEntity concert) {
        this.concertId = concert.getConcertID();
        this.concertName = concert.getConcertName();
        this.concertDate = concert.getConcertDate();
    }
}
