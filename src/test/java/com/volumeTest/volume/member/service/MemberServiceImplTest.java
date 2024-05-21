package com.volumeTest.volume.member.service;

import com.volumeTest.volume.member.entity.Member;
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
  private BCryptPasswordEncoder passwordEncoder;

  @Test
  @DisplayName("회원 생성 테스트")
  void createMember() {
    // given
    Member kim = new Member(1, "test@gmail.com", "a123456!#", "김테스트");
    Member member = memberService.createMember(kim);
    // when & then
    assertThat(member.getMemberId()).isEqualTo(kim.getMemberId());
    assertThat(member.getEmail()).isEqualTo(kim.getEmail());
    assertThat(member.getPassword()).isEqualTo(kim.getPassword());
    assertThat(member.getName()).isEqualTo(kim.getName());
  }

  @Test
  @DisplayName("회원 이메일로 조회 테스트")
  void findMemberByEmail() {
    // given
    Member kim = new Member(1, "test@gmail.com", "a123456!#", "김테스트");
    Member member = memberService.createMember(kim);

    // when
    Member findMember = memberService.findMemberByEmail(member.getEmail());

    // then
    assertThat(findMember.getMemberId()).isEqualTo(member.getMemberId());
    assertThat(findMember.getEmail()).isEqualTo(member.getEmail());
    assertThat(findMember.getPassword()).isEqualTo(member.getPassword());
    assertThat(findMember.getName()).isEqualTo(member.getName());
  }

  @Test
  @DisplayName("회원 정보 수정 테스트")
  void updateMember() {
    // given
    Member kim = new Member(1, "test@gmail.com", "a123456!#", "김테스트");
    Member member = memberService.createMember(kim);

    // when
    Member updateMember = memberService.updateMember(member, "김자바", "a123456!#");

    // then
    assertThat(updateMember.getName()).isEqualTo("김자바");
    assertThat(memberService.findMemberByEmail(member.getEmail()).getName()).isEqualTo("김자바");
  }

  @Test
  @DisplayName("회원 비밀번호 수정 테스트")
  void updateMemberPassword() {
    // given
    Member kim = new Member(1, "test@gmail.com", "a123456!#", "김테스트");
    Member member = memberService.createMember(kim);

    // when
    Member updateMember = memberService.updateMemberPassword(member, "a654321!#");
    Member findMember = memberService.findMemberByEmail(member.getEmail());

    // then
    assertTrue(passwordEncoder.matches("a654321!#", findMember.getPassword())); // 새로운 비밀번호 일치 확인
  }



  @Test
  @DisplayName("회원 탈퇴 테스트")
  void deleteMember() {
    // given
    Member kim = new Member(1, "test@gmail.com", "a123456!#", "김테스트");
    Member member = memberService.createMember(kim);

    // when
    memberService.deleteMember(member, "a123456!#");

    // then
    assertThrows(Exception.class, () -> memberService.findMemberByEmail(member.getEmail()));
  }
}