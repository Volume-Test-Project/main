package com.volumeTest.volume.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.volumeTest.volume.entity.ConcertEntity;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Getter
public class ConcertRequestDto {

    @NotNull
    private String concertName;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate concertDate;

    public ConcertEntity toEntity() {
        return ConcertEntity.builder()
                .concertName(concertName)
                .concertDate(concertDate)
                .build();
    }

}
