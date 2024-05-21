package com.volumeTest.volume.member.Controller;

import com.volumeTest.volume.member.dto.MemberDto;
import com.volumeTest.volume.member.entity.Member;
import com.volumeTest.volume.member.mapper.MemberMapper;
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

  MemberService memberService;
  MemberMapper mapper;

  // 회원 가입
  @PostMapping("/signup")
  public ResponseEntity postMember(@RequestBody @Valid MemberDto.Post memberPostDto) {

    Member member = mapper.memberPostDtoToMember(memberPostDto);
    memberService.createMember(member);

    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  // 회원조회
  @GetMapping("/info")
  public ResponseEntity getMember(@AuthenticationPrincipal String email) {

    Member findMember = memberService.findMemberByEmail(email);


    return ResponseEntity.ok(mapper.memberToMemberPostResponseDto(findMember));
  }

  // 회원정보 수정
  @PatchMapping("/mypage/update")
  public ResponseEntity patchMember(@AuthenticationPrincipal String email,
                                    @Valid @RequestBody MemberDto.Patch memberPatchDto) {

    Member findMember = memberService.findMemberByEmail(email);
    Member updateMember = memberService.updateMember(findMember, memberPatchDto.getName(), memberPatchDto.getPassword());

    return ResponseEntity.ok(mapper.memberToMemberPatchResponseDto(updateMember));
  }

  // 회원탈퇴
  @DeleteMapping("/mypage/delete")
  public ResponseEntity deleteMember(@AuthenticationPrincipal String email,
                                        @Valid  @RequestBody MemberDto.CheckPassword checkPasswordDto) {

    Member findMember = memberService.findMemberByEmail(email);
    memberService.deleteMember(findMember, checkPasswordDto.getPassword());

    return ResponseEntity.noContent().build();
  }
}
