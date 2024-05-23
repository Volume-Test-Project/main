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
    @Column(length = 20)
    private String concertName;

    @NotNull
    @Column
    private LocalDate concertDate;



}
