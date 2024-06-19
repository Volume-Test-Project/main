package com.Ttukseom.ConcertCRUD.entity;
/**
 * 콘서트 Entity
 * @author 하연
 */

import com.Ttukseom.ConcertCRUD.dto.ConcertRequestDto;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Getter
//@NoArgsConstructor
@Entity
public class ConcertEntity {
    /**
     * 콘서트 정보
     * 1. 콘서트 ID - PK
     * 2. 콘서트 이름
     * 3. 콘서트 데이터
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //JPA가 현재 사용 중인 데이터베이스에 맞춰(IDENTITY, SEQUENCE, TABLE 중) 자동으로 선택하여 기본 키를 생성
    private int concertId;

    @Column(nullable = false, length = 20)
    private String concertName;

    @Column(nullable = false)
    private LocalDate concertDate;

    public ConcertEntity() {}

    public ConcertEntity(ConcertRequestDto requestDto) {
        this.concertId = requestDto.getConcertId();
        this.concertName = requestDto.getConcertName();
        this.concertDate = requestDto.getConcertDate();
    }

    public void setConcertName(String concertName) {
    }

    public void setConcertDate(LocalDate concertDate) {
    }
}
