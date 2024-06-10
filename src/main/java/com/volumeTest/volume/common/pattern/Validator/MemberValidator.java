package com.volumeTest.volume.common.pattern.Validator;

import com.volumeTest.volume.common.exception.codeEnum.ExceptionCode;
import com.volumeTest.volume.member.entity.Member;
import com.volumeTest.volume.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberValidator {

  private final MemberRepository memberRepository;
  private final BCryptPasswordEncoder passwordEncoder;

  // Verify
  public void verifyExistsEmail(String email) {
    memberRepository.findByEmail(email)
            .ifPresent(member -> {
              throw new RuntimeException(ExceptionCode.MEMBER_EMAIL_EXISTS.getMessage());
            });
  }

  public Member findVerifyMemberByEmail(String email) {
    return memberRepository.findByEmail(email)
            .orElseThrow(() -> {
              throw new RuntimeException(ExceptionCode.MEMBER_NOT_FOUND.getMessage());
            });
  }

  public Boolean checkPassword(Member member, String password) {
    // 회원의 비밀번호 추출
    String memberPassword = member.getPassword();
    // 비밀번호가 일치하지 않으면 예외 발생
    verifyPrePasswordAndNewPasswordMatch(passwordEncoder.matches(password, memberPassword), ExceptionCode.MEMBER_PASSWORD_MISMATCH);
    // 비밀번호가 일치하면 true 반환
    return true;
  }

  public void verifyPrePasswordAndNewPasswordMatch(boolean passwordEncoder, ExceptionCode memberPasswordNotChange) {
    if (!passwordEncoder) { // 평문 비밀번호와 암호화된 비밀번호 비교
      throw new RuntimeException(memberPasswordNotChange.getMessage());
    }
  }


}
