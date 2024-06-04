package com.volumeTest.volume.member.Controller;

import com.volumeTest.volume.member.dto.MemberDto;
import com.volumeTest.volume.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
  public ResponseEntity postMember(@RequestBody @Valid MemberDto.Post memberPostDto) {
    memberService.createMember(memberPostDto);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  // 회원조회
  @GetMapping("/info")
  public ResponseEntity getMember(@AuthenticationPrincipal String email) {
    MemberDto.MemberResponse findMember = memberService.findMemberByEmail(email);
    return ResponseEntity.ok(findMember);
  }

  // 회원정보 수정
  @PutMapping("/mypage/update")
  public ResponseEntity putMember(@AuthenticationPrincipal String email,
                                    @Valid @RequestBody MemberDto.Put memberPutDto) {

    MemberDto.MemberResponse updateMember = memberService.updateMember(email, memberPutDto);

    return ResponseEntity.ok(updateMember);
  }

  // 비밀번호 변경
  @PutMapping("/mypage/update/password")
  public ResponseEntity putPassword(@AuthenticationPrincipal String email,
                                      @Valid @RequestBody MemberDto.PutPassword memberPutPasswordDto) {

    MemberDto.MemberResponse updateMemberPassword = memberService.updateMemberPassword(email, memberPutPasswordDto);

    return ResponseEntity.ok(updateMemberPassword);
  }

  // 회원탈퇴
  @DeleteMapping("/mypage/delete")
  public ResponseEntity deleteMember(@AuthenticationPrincipal String email,
                                        @Valid  @RequestBody MemberDto.Delete memberDeleteDto) {

    memberService.deleteMember(email, memberDeleteDto);

    return ResponseEntity.noContent().build();
  }
}