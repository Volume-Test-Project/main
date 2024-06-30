package com.volumeTest.volume.member.service;

import com.volumeTest.volume.member.dto.*;
import com.volumeTest.volume.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@Transactional
@SpringBootTest
class MemberServiceImplTest {

  @Autowired
  private MemberRepository memberRepository;
  @Autowired
  private MemberService memberService;

  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  @Test
  @DisplayName("회원 생성 테스트")
  void createMember() {
    // given
    MemberCreateDto memberCreateDto = new MemberCreateDto("test@gmail.com", "a123456!#", "김테스트");

    MemberResponseDto createdMember = memberService.createMember(memberCreateDto);
    // when & then
    assertThat(createdMember.getEmail()).isEqualTo(memberCreateDto.getEmail());
    assertThat(createdMember.getName()).isEqualTo(memberCreateDto.getName());
    assertThat(createdMember.getPassword()).isNotEqualTo(memberCreateDto.getPassword());
  }

  @Test
  @DisplayName("중복된 이메일로 회원 가입 시 예외 발생 테스트")
  void createMemberWithDuplicateEmail() {
    // given
    MemberCreateDto memberCreateDto = new MemberCreateDto("test@gmail.com", "a123456!#", "김테스트");
    memberService.createMember(memberCreateDto);

    // when & then
    assertThrows(Exception.class, () -> memberService.createMember(memberCreateDto)); // 이미 존재하는 이메일로 가입 시도
  }

  @Test
  @DisplayName("회원 이메일로 조회 테스트")
  void findMemberByEmail() {
    // given
    MemberCreateDto memberCreateDto = new MemberCreateDto("test@gmail.com", "a123456!#", "김테스트");
    MemberResponseDto createdMember = memberService.createMember(memberCreateDto);

    // when
    MemberResponseDto findMember = memberService.findMemberByEmail(createdMember.getEmail());

    // then
    assertThat(findMember.getMemberId()).isEqualTo(createdMember.getMemberId());
    assertThat(findMember.getEmail()).isEqualTo(createdMember.getEmail());
    assertThat(findMember.getPassword()).isEqualTo(createdMember.getPassword());
    assertThat(findMember.getName()).isEqualTo(createdMember.getName());
  }

  @Test
  @DisplayName("존재하지 않는 이메일로 회원 조회 시 예외 발생")
  void findMemberByEmailFail() {
    // given
    String invalidEmail = "invalid@email.com";

    // when & then
    assertThrows(Exception.class, () -> memberService.findMemberByEmail(invalidEmail));
  }

  @Test
  @Transactional
  @DisplayName("회원 정보 수정 테스트")
  void updateMember() {
    // given
    MemberCreateDto memberCreateDto = new MemberCreateDto("test@gmail.com", "a123456!#", "김테스트");
    MemberResponseDto createdMember = memberService.createMember(memberCreateDto);

    MemberUpdateDto memberUpdateDto = new MemberUpdateDto(createdMember.getEmail(), "a123456!#", "김자바");

    // when
    MemberResponseDto updatedMemberResponse = memberService.updateMember(createdMember.getEmail(), memberUpdateDto);

    // then
    assertThat(updatedMemberResponse.getName()).isEqualTo("김자바");
    assertThat(memberService.findMemberByEmail(updatedMemberResponse.getEmail()).getName()).isEqualTo("김자바");
  }

  @Test
  @DisplayName("잘못된 비밀번호로 회원 정보 수정 시 예외 발생 테스트")
  void updateMemberWithWrongPassword() {
    // given
    MemberCreateDto memberCreateDto = new MemberCreateDto("test@gmail.com", "a123456!#", "김테스트");
    MemberResponseDto createdMember = memberService.createMember(memberCreateDto);

    MemberUpdateDto memberUpdateDto = new MemberUpdateDto(createdMember.getEmail(), "wrongPassword", createdMember.getName());

    // when & then
    assertThrows(Exception.class, () -> memberService.updateMember(createdMember.getEmail(), memberUpdateDto));
  }

  @Test
  @Transactional
  @DisplayName("회원 비밀번호 수정 테스트")
  void updateMemberPassword() {
    // given
    MemberCreateDto memberCreateDto = new MemberCreateDto("test@gmail.com", "a123456!#", "김테스트");
    MemberResponseDto createdMember = memberService.createMember(memberCreateDto);

    MemberUpdatePasswordDto memberUpdatePasswordDto = new MemberUpdatePasswordDto(createdMember.getEmail(), "a654321!#");

    // when
    MemberResponseDto updatedPasswordMember = memberService.updateMemberPassword(createdMember.getEmail(), memberUpdatePasswordDto);
    MemberResponseDto findMember = memberService.findMemberByEmail(updatedPasswordMember.getEmail());

    // then
    assertThat(findMember.getPassword()).isEqualTo(updatedPasswordMember.getPassword());
  }

  @Test
  @DisplayName("동일한 비밀번호로 비밀번호 변경 시도 시 예외 발생 테스트")
  void updateMemberPasswordWithSamePassword() {
    // given
    MemberCreateDto memberCreateDto = new MemberCreateDto("test@gmail.com", "a123456!#", "김테스트");
    MemberResponseDto createdMember = memberService.createMember(memberCreateDto);

    MemberUpdatePasswordDto memberUpdatePasswordDto = new MemberUpdatePasswordDto(createdMember.getEmail(), "a123456!#");
    // when & then
    assertThrows(Exception.class, () -> memberService.updateMemberPassword(createdMember.getEmail(), memberUpdatePasswordDto));
  }

  @Test
  @Transactional
  @DisplayName("회원 탈퇴 테스트")
  void deleteMember() {
    // given
    MemberCreateDto memberCreateDto = new MemberCreateDto("test@gmail.com", "a123456!#", "김테스트");
   MemberResponseDto createdMember = memberService.createMember(memberCreateDto);

    MemberDeleteDto deleteMemberDto = new MemberDeleteDto(createdMember.getEmail(), "a123456!#");

    // when
    memberService.deleteMember(createdMember.getEmail(), deleteMemberDto);

    // then
    assertThrows(Exception.class, () -> memberService.findMemberByEmail(createdMember.getEmail()));
  }

  @Test
  @Transactional
  @DisplayName("잘못된 비밀번호로 회원 탈퇴 시도 시 예외 발생 테스트")
  void deleteMemberWithWrongPassword() {
    // given
    MemberCreateDto memberCreateDto = new MemberCreateDto("test@gmail.com", "a123456!#", "김테스트");
   MemberResponseDto createdMember = memberService.createMember(memberCreateDto);

    MemberDeleteDto deleteMemberDto = new MemberDeleteDto(createdMember.getEmail(), "wrongPassword");

    // when & then
    assertThrows(Exception.class, () -> memberService.deleteMember(createdMember.getEmail(), deleteMemberDto));
  }

//  @Test
//  @DisplayName("로그인 성공 테스트")
//  void loginSuccess() {
//    // given
//    Member kim = new Member(1, "test@test.com", "a123456!@", "김테스트");
//
//  }
}