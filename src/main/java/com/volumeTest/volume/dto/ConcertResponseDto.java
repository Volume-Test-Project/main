package com.volumeTest.volume.dto;

import com.volumeTest.volume.entity.ConcertEntity;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.Date;

@Builder
public class ConcertDto {

    @NotNull
    private int concertId;
    private String concertName;
    private Date concertDate;

    public ConcertDto(int concertId, String concertName, Date concertDate) {
        this.concertId = concertId;
        this.concertName = concertName;
        this.concertDate = concertDate;
    }

    public ConcertEntity toEntity() {
        return ConcertEntity.builder()
                .concertName(concertName)
                .concertDate(concertDate)
                .build();
    }

    public ConcertDto toDto(ConcertEntity concert) {
        return ConcertDto.builder()
                .concertId(concert.getConcertID())
                .concertName(concert.getConcertName())
                .concertDate(concert.getConcertDate())
                .build();
    }
}
