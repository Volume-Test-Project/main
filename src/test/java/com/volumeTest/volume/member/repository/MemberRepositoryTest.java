package com.volumeTest.volume.member.repository;

import com.volumeTest.volume.member.entity.Member;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@Transactional
@DataJpaTest
class MemberRepositoryTest {

  @Autowired
  private MemberRepository memberRepository;

  Member kim;

  @BeforeEach
  void setUp() {
    kim = new Member(1, "test@gmail.com", "a123456!#", "김테스트");
    memberRepository.save(kim);
  }

//  @AfterEach
//  void tearDown() {
//    memberRepository.deleteAll();
//  }

  @Test
  @DisplayName("회원 생성 시 이메일 중복 체크탐지 테스트")
  void createMemberEmailCheck() {
    // given
    Member hong = new Member(2, "test@gmail.com", "a654321!#", "홍길동");

    // when & then
    assertThrows(Exception.class, () -> memberRepository.save(hong));
  }

  @Test
  @DisplayName("회원 생성 시 패스워드 길이 초과 테스트")
  void createMemberPasswordLengthValidation() {
    // given
    Member hong = new Member(2, "hong@gmail.com", "1234567890123456789901", "홍길동");

    // when & then
    assertThrows(Exception.class, () -> memberRepository.save(hong));
  }

  @Test
  @DisplayName("회원 생성 시 이메일 길이 초과 테스트")
  void createMemberEmailLengthValidation() {
    // given
    Member hong = new Member(2, "hong@gmailandkakakoandnaverzzzhaha.com", "a123456!#", "홍길동");

    // when & then
    assertThrows(Exception.class, () -> memberRepository.save(hong));
  }

  @Test
  @DisplayName("회원 생성 시 이름 길이 초과 테스트")
  void createMemberNameLengthValidation() {
    // given
    Member hong = new Member(2, "hong@gmail.com", "a123456!#", "홍길동그라미어캣타워싱턴테이블레이저수지역삼성전자전거위로이킴민재");

    // when & then
    assertThrows(Exception.class, () -> memberRepository.save(hong));
  }

  @Test
  @DisplayName("NULL 허용 방지 테스트")
  void createMemberNullValidation() {
    // given
    Member hong = new Member(2, null, "a123456!#", "홍길동");
    Member park = new Member(3, "park@gmail.com", null, "박철준");
    Member lee = new Member(4, "lee@gmail.com", "a123456!#", null);

    // when & then
    assertThrows(Exception.class, () -> memberRepository.save(hong));
    assertThrows(Exception.class, () -> memberRepository.save(park));
    assertThrows(Exception.class, () -> memberRepository.save(lee));
  }

  @Test
  @DisplayName("이메일로 회원 조회 성공 테스트")
  void findByEmail() {
    // given
    String email = "test@gmail.com";

    // when
    Member member = memberRepository.findByEmail(email).orElse(null);
    log.info("member.getEmail() = {}", member.getEmail());

    // then
    assertNotNull(member);
    assertEquals(member.getEmail(), kim.getEmail());
  }

  @Test
  @DisplayName("이메일로 회원 조회 실패 테스트")
  void findByEmailFail() {
    // given
    String email = "hong@gmail.com";

    // when
    Member member = memberRepository.findByEmail(email).orElse(null);

    // then
    assertNull(member);
    }
}