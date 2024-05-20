package com.volumeTest.volume.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConcertEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long concertID;

    private String concertName;
    private Date concertDate;



}
