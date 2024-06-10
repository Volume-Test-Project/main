package com.volumeTest.volume.concert.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CONCERTS")
public class Concert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long concertID;

    @NotNull
    @Column(length = 20)
    private String concertName;

    @NotNull
    private LocalDate concertDate;

}
