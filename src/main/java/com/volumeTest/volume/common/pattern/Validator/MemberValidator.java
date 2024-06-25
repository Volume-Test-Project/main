package com.volumeTest.volume.common.pattern.Validator;

import com.volumeTest.volume.common.exception.ExceptionStatus;
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
              throw new RuntimeException(ExceptionStatus.MEMBER_EMAIL_EXISTS.getMessage());
            });
  }

  public Member findVerifyMemberByEmail(String email) {
    return memberRepository.findByEmail(email)
            .orElseThrow(() -> {
              throw new RuntimeException(ExceptionStatus.MEMBER_NOT_FOUND.getMessage());
            });
  }

  public void verifyPasswordCorrect(Member member, String password, ExceptionStatus exceptionStatus) {
    String encodedPassword = member.getPassword();
    if (!passwordEncoder.matches(password, encodedPassword)) {
      throw new IllegalArgumentException(exceptionStatus.getMessage());
    }
  }

  public void verifyPasswordUnCorrect(Member member, String password, ExceptionStatus exceptionStatus) {
    String encodedPassword = member.getPassword();
    if (passwordEncoder.matches(password, encodedPassword)) {
      throw new IllegalArgumentException(exceptionStatus.getMessage());
    }
  }

  public void checkPasswordForMemberUpdatePassword(Member member, String password) {
    verifyPasswordUnCorrect(member, password, ExceptionStatus.MEMBER_PASSWORD_NOT_CHANGE);
  }

  public void checkPasswordForMemberUpdateAndDelete(Member member, String password) {
    verifyPasswordCorrect(member, password, ExceptionStatus.MEMBER_PASSWORD_MISMATCH);
  }
}
