package com.volumeTest.volume.dto;

import com.volumeTest.volume.entity.ConcertEntity;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class ConcertResponseDto {

    @NotNull
    private Long concertId;
    private String concertName;
    private Date concertDate;

}
