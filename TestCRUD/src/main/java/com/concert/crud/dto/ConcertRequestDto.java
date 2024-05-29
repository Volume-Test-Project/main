package com.concert.crud.dto;

import com.concert.crud.entity.Concert;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDate;

@Getter
@Builder
public class ConcertRequestDto {
    private int concertId;
    private String concertName;
    private LocalDate concertDate;

        public Concert toEntity(ConcertRequestDto dto) {
            return Concert.builder()
                    .concertId(dto.getConcertId())
                    .concertName(dto.getConcertName())
                    .concertDate(dto.getConcertDate())
                    .build();
        }

}
