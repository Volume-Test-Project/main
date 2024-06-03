package com.volumeTest.volume.concert.dto;

import com.volumeTest.volume.concert.entity.ConcertEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
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
