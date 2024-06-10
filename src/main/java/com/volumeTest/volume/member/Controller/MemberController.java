package com.volumeTest.volume.member.Controller;

import static com.volumeTest.volume.common.util.ApiResponseUtil.success;

import com.volumeTest.volume.common.util.ApiResponseUtil;
import com.volumeTest.volume.common.util.ApiResponseUtil.ApiResult;
import com.volumeTest.volume.member.dto.MemberDto;
import com.volumeTest.volume.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<Object> postMember(@RequestBody @Valid MemberDto.Post memberPostDto) {
    memberService.createMember(memberPostDto);
    return new ResponseEntity<>(success("회원가입이 완료되었습니다."), new HttpHeaders(), HttpStatus.CREATED);
  }

  // 회원조회
  @GetMapping("/info")
  public ResponseEntity<Object> getMember(@AuthenticationPrincipal String email) {
    MemberDto.MemberResponse findMember = memberService.findMemberByEmail(email);
    return new ResponseEntity<>(success(findMember), new HttpHeaders(), HttpStatus.OK);
  }

  // 회원정보 수정
  @PutMapping("/mypage/update")
  public ResponseEntity<Object> putMember(@AuthenticationPrincipal String email,
                                    @Valid @RequestBody MemberDto.Put memberPutDto) {

    MemberDto.MemberResponse updateMember = memberService.updateMember(email, memberPutDto);

    return new ResponseEntity<>(success(updateMember),new HttpHeaders(), HttpStatus.OK);
  }

  // 비밀번호 변경
  @PutMapping("/mypage/update/password")
  public ResponseEntity<Object> putPassword(@AuthenticationPrincipal String email,
                                      @Valid @RequestBody MemberDto.PutPassword memberPutPasswordDto) {

    MemberDto.MemberResponse updateMemberPassword = memberService.updateMemberPassword(email, memberPutPasswordDto);

    return new ResponseEntity<>(success(updateMemberPassword), new HttpHeaders(), HttpStatus.OK);
  }

  // 회원탈퇴
  @DeleteMapping("/mypage/delete")
  public ResponseEntity<Object> deleteMember(@AuthenticationPrincipal String email,
                                        @Valid  @RequestBody MemberDto.Delete memberDeleteDto) {

    memberService.deleteMember(email, memberDeleteDto);

    return new ResponseEntity<>(success("삭제되었습니다."), new HttpHeaders(), HttpStatus.OK);
  }
}