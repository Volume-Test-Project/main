package com.volumeTest.volume.dto;

import com.volumeTest.volume.entity.ConcertEntity;
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

    @NotNull
    private String concertName;

    @NotNull
    private LocalDate concertDate;

}
