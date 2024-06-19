package com.Ttukseom.ConcertCRUD.Controller;

import com.Ttukseom.ConcertCRUD.dto.ConcertRequestDto;
import com.Ttukseom.ConcertCRUD.dto.ConcertResponseDto;
import com.Ttukseom.ConcertCRUD.dto.SuccessResponseDto;
import com.Ttukseom.ConcertCRUD.service.ConcertService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/concerts")
@RequiredArgsConstructor
public class ConcertController {
    private final ConcertService concertService; // 호출된 URL에 따라 메서드를 실행시킬 때, Service에서 구현된 메서드를 실행시킬 것이므로 Service 객체를 선언한다.

    @GetMapping("/concertList")
    public List<ConcertResponseDto> getPosts() {
        return concertService.getPosts();
    }

    @PostMapping("/concert")
    public ConcertResponseDto createPost(@RequestBody ConcertRequestDto requestDto) {
        return concertService.createPost(requestDto);
    }

    @GetMapping("/{concertId}")
    public ConcertResponseDto getPost(@PathVariable int concertId) {
        return concertService.getPost(concertId);
    }

    @PutMapping("/{concertId}")
    public ConcertResponseDto updatePost(@PathVariable int concertId, @RequestBody ConcertRequestDto requestDto) throws Exception {
        return concertService.updatePost(concertId, requestDto);
    }

    @DeleteMapping("/{concertId}")
    public SuccessResponseDto deletePost(@PathVariable int concertId, @RequestBody ConcertRequestDto requestDto) throws Exception {
        return concertService.deletePost(concertId, requestDto);
    }
}
