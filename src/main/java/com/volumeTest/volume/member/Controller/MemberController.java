package com.volumeTest.volume.member.Controller;

import static com.volumeTest.volume.common.util.ApiResponseUtil.success;

import com.volumeTest.volume.member.dto.*;
import com.volumeTest.volume.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/member")
public class MemberController {

  private final MemberService memberService;

  // 회원 가입
  @PostMapping("/signup")
  public ResponseEntity<Object> postMember(@RequestBody @Valid MemberCreateDto memberCreateDto) {
    memberService.createMember(memberCreateDto);
    return new ResponseEntity<>(success("회원가입이 완료되었습니다."), new HttpHeaders(), HttpStatus.CREATED);
  }

  // 로그인
  @PostMapping("/login")
  public ResponseEntity<Object> login(@RequestBody MemberLoginRequestDto request) {
      return new ResponseEntity<>(success(memberService.login(request)), new HttpHeaders(), HttpStatus.OK);
  }

  //내 정보 조회
  @GetMapping("/me")
  public ResponseEntity<Object> me(Authentication authentication) {
    return new ResponseEntity<>(success(authentication.getPrincipal()), new HttpHeaders(), HttpStatus.OK);
  }

  // 회원조회
  @GetMapping("/info")
  public ResponseEntity<Object> getMember(@AuthenticationPrincipal String email) {
    MemberResponseDto findMember = memberService.findMemberByEmail(email);
    return new ResponseEntity<>(success(findMember), new HttpHeaders(), HttpStatus.OK);
  }

  // 회원정보 수정
  @PutMapping("/mypage/update")
  public ResponseEntity<Object> putMember(@AuthenticationPrincipal String email,
                                    @Valid @RequestBody MemberUpdateDto memberUpdateDto) {

    MemberResponseDto updateMember = memberService.updateMember(email, memberUpdateDto);

    return new ResponseEntity<>(success(updateMember),new HttpHeaders(), HttpStatus.OK);
  }

  // 비밀번호 변경
  @PutMapping("/mypage/update/password")
  public ResponseEntity<Object> putPassword(@AuthenticationPrincipal String email,
                                      @Valid @RequestBody MemberUpdatePasswordDto memberUpdatePasswordDto) {

    MemberResponseDto updateMemberPassword = memberService.updateMemberPassword(email, memberUpdatePasswordDto);

    return new ResponseEntity<>(success(updateMemberPassword), new HttpHeaders(), HttpStatus.OK);
  }

  // 회원탈퇴
  @DeleteMapping("/mypage/delete")
  public ResponseEntity<Object> deleteMember(@AuthenticationPrincipal String email,
                                        @Valid  @RequestBody MemberDeleteDto memberDeleteDto) {

    memberService.deleteMember(email, memberDeleteDto);

    return new ResponseEntity<>(success("삭제되었습니다."), new HttpHeaders(), HttpStatus.OK);
  }
}