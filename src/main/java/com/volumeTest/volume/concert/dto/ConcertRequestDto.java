package com.volumeTest.volume.concert.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.volumeTest.volume.concert.entity.Concert;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class ConcertRequestDto {

    @NotBlank(message = "문자를 입력해주세요")
    private String concertName;

    @NotBlank(message = "문자를 입력해주세요")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Pattern(regexp = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$\n", message = "날짜 형식이 일치하지 않습니다")
    private LocalDate concertDate;

    public Concert toEntity() {
        return Concert.builder()
                .concertName(concertName)
                .concertDate(concertDate)
                .build();
    }

}
