package com.concert.crud.entity;
/**
 * 콘서트 Entity
 * @author 하연
 */

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity(name="concert")
@Getter
//@Builder
@NoArgsConstructor
//@AllArgsConstructor
@Table(name = "CONCERTS")
public class Concert {
    /**
     * 콘서트 정보
     * 1. 콘서트 ID - PK
     * 2. 콘서트 이름
     * 3. 콘서트 데이터
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int concertId;

    @Column(nullable = false, length = 20)
    private String concertName;

    @Column(nullable = false)
    private LocalDate concertDate;

}
