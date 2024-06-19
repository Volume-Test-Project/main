package com.Ttukseom.ConcertCRUD.service;

import com.Ttukseom.ConcertCRUD.dto.ConcertRequestDto;
import com.Ttukseom.ConcertCRUD.dto.ConcertResponseDto;
import com.Ttukseom.ConcertCRUD.entity.ConcertEntity;
import com.Ttukseom.ConcertCRUD.repository.ConcertRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ConcertServiceTest {
    @Mock
    private ConcertRepository concertRepository;

    @InjectMocks
    private ConcertService concertService;

    @Test
    public void testFindConcertById() {
        MockitoAnnotations.openMocks(this);

        ConcertRequestDto requestDto = ConcertRequestDto.builder()
                .concertId(1)
                .concertName("Concert")
                .concertDate(LocalDate.now())
                .build();
        ConcertEntity concert = new ConcertEntity(requestDto);

        when(concertRepository.findById(1)).thenReturn(Optional.of(concert));

        ConcertResponseDto foundConcert = concertService.getPost(1);

        assertThat(foundConcert.getConcertName()).isEqualTo("Concert");
    }
}