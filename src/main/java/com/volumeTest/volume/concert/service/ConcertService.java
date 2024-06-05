package com.volumeTest.volume.concert.service;

import com.volumeTest.volume.common.exception.ExceptionStatus;
import com.volumeTest.volume.common.exception.VolumeTestException;
import com.volumeTest.volume.concert.dto.ConcertRequestDto;
import com.volumeTest.volume.concert.dto.ConcertResponseDto;
import com.volumeTest.volume.concert.entity.ConcertEntity;
import com.volumeTest.volume.concert.repository.ConcertRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ConcertService {

    private ConcertRepository concertRepository;

    public ConcertResponseDto createConcert(@Validated ConcertRequestDto concertRequestDto) {
        ConcertEntity concert = concertRepository.save(concertRequestDto.toEntity());

        return  new ConcertResponseDto(concert);
    }

    public ConcertResponseDto findById(Long id) {
        ConcertEntity concert = concertRepository.findById(id)
                .orElseThrow(() -> new VolumeTestException(ExceptionStatus.CONCERT_NOT_FOUND));

        return new ConcertResponseDto(concert);
    }

    public List<ConcertResponseDto> findAll() {
        List<ConcertEntity> concertList =  concertRepository.findAll();
        List<ConcertResponseDto> dtoList = concertList.stream()
                .map(ConcertResponseDto::new)
                .collect(Collectors.toList());
        return dtoList;
    }

    public ConcertResponseDto updateConcert(Long id, @Validated ConcertRequestDto concertRequestDto){
        ConcertEntity foundConcert = ConcertEntity.builder()
                .concertID(id)
                .concertName(concertRequestDto.getConcertName())
                .concertDate(concertRequestDto.getConcertDate())
                .build();

        ConcertEntity updateConcert = concertRepository.save(foundConcert);

        return new ConcertResponseDto(updateConcert);
    }

    public void deleteConcert(Long id) {
        ConcertEntity concert = concertRepository.findById(id)
                .orElseThrow(() -> new VolumeTestException(ExceptionStatus.CONCERT_NOT_FOUND));

        concertRepository.delete(concert);
    }


}
