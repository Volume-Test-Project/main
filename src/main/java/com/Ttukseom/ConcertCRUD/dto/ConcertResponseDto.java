package com.Ttukseom.ConcertCRUD.dto;

import com.Ttukseom.ConcertCRUD.entity.ConcertEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class ConcertResponseDto {

    private int Id;
    private String concertName;
    private LocalDate concertDate;

    public ConcertResponseDto(ConcertEntity entity) { //게시글을 반환해야하는 경우의 데이터 필드 값을 모아 ConcertResponseDtofh rngus
        this.Id = entity.getConcertId();
        this.concertName = entity.getConcertName();
        this.concertDate = entity.getConcertDate();
    }
}
