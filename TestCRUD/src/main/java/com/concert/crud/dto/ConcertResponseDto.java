package com.concert.crud.dto;

import com.concert.crud.entity.Concert;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@ToString
public class ConcertResponseDto {
    private int Id;
    private String concertName;
    private LocalDate concertDate;

    public Concert toEntity(ConcertResponseDto dto) {
        return Concert.builder()
                .concertId(dto.getId())
                .concertName(dto.getConcertName())
                .concertDate(dto.getConcertDate())
                .build();
    }
}
