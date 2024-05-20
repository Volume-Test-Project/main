package com.volumeTest.volume.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
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

    @NotNull
    private String concertName;
    @NotNull
    private LocalDate concertDate;



}
