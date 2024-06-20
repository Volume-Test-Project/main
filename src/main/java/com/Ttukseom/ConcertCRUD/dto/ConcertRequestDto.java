package com.Ttukseom.ConcertCRUD.dto;

import lombok.Builder;
import lombok.Getter;
import java.time.LocalDate;

@Getter
@Builder
public class ConcertRequestDto {

    private int concertId;
    private String concertName;
    private LocalDate concertDate;
}
