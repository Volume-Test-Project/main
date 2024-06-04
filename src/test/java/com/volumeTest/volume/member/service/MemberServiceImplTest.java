package com.volumeTest.volume.member.service;

import com.volumeTest.volume.member.dto.MemberDto;
import com.volumeTest.volume.member.entity.Member;
import com.volumeTest.volume.member.mapper.MemberMapper;
import com.volumeTest.volume.member.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
  private MemberService memberService;

  @Autowired
  private MemberMapper memberMapper;

  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  @Test
  @DisplayName("회원 생성 테스트")
  void createMember() {
    // given
    MemberDto.Post memberPostDto = new MemberDto.Post("test@gmail.com", "a123456!#", "김테스트");

    MemberDto.MemberResponse createdMember = memberService.createMember(memberPostDto);
    // when & then
    assertThat(createdMember.getEmail()).isEqualTo(memberPostDto.getEmail());
    assertThat(createdMember.getName()).isEqualTo(memberPostDto.getName());
    assertThat(createdMember.getPassword()).isNotEqualTo(memberPostDto.getPassword());
  }

  @Test
  @DisplayName("중복된 이메일로 회원 가입 시 예외 발생 테스트")
  void createMemberWithDuplicateEmail() {
    // given
    MemberDto.Post memberPostDto = new MemberDto.Post("test@gmail.com", "a123456!#", "김테스트");
    memberService.createMember(memberPostDto);

    // when & then
    assertThrows(Exception.class, () -> memberService.createMember(memberPostDto)); // 이미 존재하는 이메일로 가입 시도
  }

  @Test
  @DisplayName("회원 이메일로 조회 테스트")
  void findMemberByEmail() {
    // given
    MemberDto.Post memberPostDto = new MemberDto.Post("test@gmail.com", "a123456!#", "김테스트");
    MemberDto.MemberResponse createdMember = memberService.createMember(memberPostDto);

    // when
    MemberDto.MemberResponse findMember = memberService.findMemberByEmail(createdMember.getEmail());

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
  @DisplayName("회원 정보 수정 테스트")
  void updateMember() {
    // given
    MemberDto.Post memberPostDto = new MemberDto.Post("test@gmail.com", "a123456!#", "김테스트");
    MemberDto.MemberResponse createdMember = memberService.createMember(memberPostDto);

    MemberDto.Put memberPutDto = new MemberDto.Put(createdMember.getEmail(), createdMember.getPassword(), "김자바");

    // when
    MemberDto.MemberResponse updatedMemberResponse = memberService.updateMember(createdMember.getEmail(), memberPutDto);

    // then
    assertThat(updatedMemberResponse.getName()).isEqualTo("김자바");
    assertThat(memberService.findMemberByEmail(updatedMemberResponse.getEmail()).getName()).isEqualTo("김자바");
  }

  @Test
  @DisplayName("잘못된 비밀번호로 회원 정보 수정 시 예외 발생 테스트")
  void updateMemberWithWrongPassword() {
    // given
    MemberDto.Post memberPostDto = new MemberDto.Post("test@gmail.com", "a123456!#", "김테스트");
    MemberDto.MemberResponse createdMember = memberService.createMember(memberPostDto);

    MemberDto.Put memberPutDto = new MemberDto.Put(createdMember.getEmail(), "wrongPassword", createdMember.getName());

    // when & then
    assertThrows(Exception.class, () -> memberService.updateMember(createdMember.getEmail(), memberPutDto));
  }

  @Test
  @DisplayName("회원 비밀번호 수정 테스트")
  void updateMemberPassword() {
    // given
    MemberDto.Post memberPostDto = new MemberDto.Post("test@gmail.com", "a123456!#", "김테스트");
    MemberDto.MemberResponse createdMember = memberService.createMember(memberPostDto);

    MemberDto.PutPassword memberPutPasswordDto = new MemberDto.PutPassword(createdMember.getEmail(), "a654321!#");

    // when
    MemberDto.MemberResponse updatedPasswordMember = memberService.updateMemberPassword(createdMember.getEmail(), memberPutPasswordDto);
    MemberDto.MemberResponse findMember = memberService.findMemberByEmail(updatedPasswordMember.getEmail());

    // then
    assertTrue(passwordEncoder.matches("a654321!#", findMember.getPassword())); // 새로운 비밀번호 일치 확인
  }

  @Test
  @DisplayName("동일한 비밀번호로 비밀번호 변경 시도 시 예외 발생 테스트")
  void updateMemberPasswordWithSamePassword() {
    // given
    MemberDto.Post memberPostDto = new MemberDto.Post("test@gmail.com", "a123456!#", "김테스트");
    MemberDto.MemberResponse createdMember = memberService.createMember(memberPostDto);

    MemberDto.PutPassword memberPutPasswordDto = new MemberDto.PutPassword(createdMember.getEmail(), "a123456!#");
    // when & then
    assertThrows(Exception.class, () -> memberService.updateMemberPassword(createdMember.getEmail(), memberPutPasswordDto));
  }

//  @Test
//  @DisplayName("회원 탈퇴 테스트")
//  void deleteMember() {
//    // given
//    MemberDto.Post memberPostDto = new MemberDto.Post("test@gmail.com", "a123456!#", "김테스트");
//    MemberDto.MemberResponse createdMember = memberService.createMember(memberPostDto);
//
//    MemberDto.Delete deleteMemberDto = new MemberDto.Delete(createdMember.getEmail(), createdMember.getPassword());
//
//    // when
//    memberService.deleteMember(createdMember.getEmail(), deleteMemberDto);
//
//    // then
//    assertThrows(Exception.class, () -> memberService.findMemberByEmail(createdMember.getEmail()));
//  }
//
//  @Test
//  @DisplayName("잘못된 비밀번호로 회원 탈퇴 시도 시 예외 발생 테스트")
//  void deleteMemberWithWrongPassword() {
//    // given
//    MemberDto.Post memberPostDto = new MemberDto.Post("test@gmail.com", "a123456!#", "김테스트");
//    MemberDto.MemberResponse createdMember = memberService.createMember(memberPostDto);
//
//    MemberDto.Delete deleteMemberDto = new MemberDto.Delete(createdMember.getEmail(), "wrongPassword");
//
//    // when & then
//    assertThrows(Exception.class, () -> memberService.deleteMember(createdMember.getEmail(), deleteMemberDto));
//  }
}