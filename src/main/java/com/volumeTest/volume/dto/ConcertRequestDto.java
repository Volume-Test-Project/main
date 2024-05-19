package com.volumeTest.volume.dto;

import com.volumeTest.volume.entity.ConcertEntity;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter
public class ConcertRequestDto {

    private String concertName;
    private Date concertDate;

    public ConcertEntity toEntity() {
        return ConcertEntity.builder()
                .concertName(concertName)
                .concertDate(concertDate)
                .build();
    }

}
