package com.Ttukseom.ConcertCRUD.service;

import com.Ttukseom.ConcertCRUD.dto.ConcertRequestDto;
import com.Ttukseom.ConcertCRUD.dto.SuccessResponseDto;
import com.Ttukseom.ConcertCRUD.dto.ConcertResponseDto;
import com.Ttukseom.ConcertCRUD.entity.ConcertEntity;
import com.Ttukseom.ConcertCRUD.repository.ConcertRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class ConcertService {
    private final ConcertRepository concertRepository;

    @Transactional(readOnly = true) //메서드가 데이터베이스 트랜잭션 내에서 실행되도록 지정하며, 읽기 전용 트랜잭션임을 나타낸다.
    public List<ConcertResponseDto> getPosts() { //List<ConcertResponseDto> 타입의 결과를 반환한다.
        return concertRepository.findAllByModifiedAtDesc().stream() //findAllByModifiedAtDesc 메서드를 호출하여 수정된 날짜 기준으로 내림차순으로 정렬된 모든 콘서트 엔티티 리스트를 가져온다.
                .map(ConcertResponseDto::new) //각 엔티티 객체를 ConcertResponseDto 객체로 변환, ConcertResponseDto::new는 ConcertResponseDto 생성자를 호출하여 새로운 ConcertResponseDto 객체를 생성 & 이를 통해 각 엔티티 객체가 DTO 객체로 매핑된다.
                .collect(Collectors.toList()); //스트림의 모든 요소를 리스트로 수집하여 반환
    }

    @Transactional
    public ConcertResponseDto createPost(ConcertRequestDto requestDto) {
        ConcertEntity concert = new ConcertEntity(requestDto);
        concertRepository.save(concert);
        return new ConcertResponseDto(concert);
    }

    @Transactional
    public ConcertResponseDto getPost(int concertId) {
        return concertRepository.findById(concertId)
                .map(ConcertResponseDto::new)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 콘서트입니다.")); //람다 표현식으로, 값이 존재하지 않을 때 실행될 코드
    }

    @Transactional
    public ConcertResponseDto updatePost(int concertId, ConcertRequestDto requestDto) {
        ConcertEntity concert = concertRepository.findById(concertId).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        concert.setConcertName(requestDto.getConcertName());
        concert.setConcertDate(requestDto.getConcertDate());
        concertRepository.save(concert); // 변경된 엔티티를 저장
        return new ConcertResponseDto(concert);

        //ConcertRepository.update(requestDto); //Spring Data JPA의 JpaRepository 인터페이스에는 update 메서드가 기본적으로 제공되지 않는다네..?
        //return new ConcertResponseDto(concert);
    }

    @Transactional
    public SuccessResponseDto deletePost(int concertId, ConcertRequestDto requestDto) {
        ConcertEntity concert = concertRepository.findById(concertId).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );

        concertRepository.deleteById(concertId);
        return new SuccessResponseDto(true);
    }
}
