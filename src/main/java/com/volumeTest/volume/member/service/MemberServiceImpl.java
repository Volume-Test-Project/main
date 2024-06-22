package com.volumeTest.volume.member.service;

import com.volumeTest.volume.common.exception.ExceptionStatus;
import com.volumeTest.volume.common.exception.VolumeTestException;
import com.volumeTest.volume.common.security.jwt.JwtProvider;
import com.volumeTest.volume.member.dto.MemberDto;
import com.volumeTest.volume.member.dto.MemberLoginRequestDto;
import com.volumeTest.volume.member.dto.MemberLoginResponseDto;
import com.volumeTest.volume.member.entity.Member;
import com.volumeTest.volume.member.mapper.MemberMapper;
import com.volumeTest.volume.member.repository.MemberRepository;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

  private final MemberRepository memberRepository;
  private final BCryptPasswordEncoder passwordEncoder;
  private final MemberMapper memberMapper;
  private final JwtProvider jwtProvider;

  @Override
  public MemberDto.MemberPostResponse createMember(MemberDto.Post memberPostDto) {
    verifyExistsEmail(memberPostDto.getEmail());
    // 비밀번호 암호화
    String encryptedPassword = passwordEncoder.encode(memberPostDto.getPassword());

    MemberDto.MemberPostResponse response = memberMapper.memeberPostDtoToResponse(memberPostDto, encryptedPassword);

    Member savedMember = memberRepository.save(memberMapper.memberPostDtoToMember(memberPostDto, encryptedPassword));
    return response;
  }

  /**
   * 로그인
   * @param request MemberLoginRequestDto
   * @return MemberLoginResponseDto
   * @throws VolumeTestException 로그인 실패시 ExceptionStatus.MEMBER_LOGIN_FAIL
   */
  @Override
  public MemberLoginResponseDto login(MemberLoginRequestDto request) {
    //이메일로 회원 조회 없으면 forbidden
    Member member = memberRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new VolumeTestException(ExceptionStatus.MEMBER_LOGIN_FAIL));

    // 조회한 회원과 입력한 비밀번호가 일치하지 않으면 forbidden
    if(!checkPassword(member, request.getPassword()))
      throw new VolumeTestException(ExceptionStatus.MEMBER_LOGIN_FAIL);

    // AccessToken, RefreshToken 생성
    String accessToken = jwtProvider.generateToken(member, Duration.ofMinutes(30));
    String refreshToken = jwtProvider.generateToken(member, Duration.ofDays(7));

    // AccessToken, RefreshToken 반환
    return MemberLoginResponseDto.builder()
            .accessToken(accessToken)
            .refreshToken(refreshToken)
            .build();
  }

  @Override
  public Member findMemberByEmail(String email) {
    return findVerifyMemberByEmail(email);
  }

  @Override
  public Member updateMember(Member member, String name, String password) {
    // 회원 조회
    Member findMember = findVerifyMemberByEmail(member.getEmail());

    checkPassword(findMember, password);

    // 변경할 정보 저장
    findMember.updateName(name);

    // 변경된 정보 저장
    Member updatedMember = memberRepository.save(findMember);

    log.info("{}님의 정보가 수정되었습니다. 수정된 정보: 이름={}, 이메일={}", findMember.getName(), updatedMember.getName(), updatedMember.getEmail());
    return updatedMember;
  }

  @Override
  public Member updateMemberPassword(Member member, String password) {
    Member findMember = findVerifyMemberByEmail(member.getEmail());

    // 새로운 비밀번호와 이전 비밀번호가 같으면 변경하지 않음
    if (passwordEncoder.matches(password, findMember.getPassword())) { // 평문 비밀번호와 암호화된 비밀번호 비교
      throw new VolumeTestException(ExceptionStatus.MEMBER_PASSWORD_NOT_CHANGE);
    }

    // 변경할 비밀번호 암호화
    String encryptedPassword = passwordEncoder.encode(password);

    // 변경할 암호화된 비밀번호 저장
    findMember.updatePassword(encryptedPassword);

    // 변경된 정보 저장
    Member updatedMember = memberRepository.save(findMember);

    return updatedMember;
  }


  @Override
  public void deleteMember(Member member, String password) {
    // 패스워드로 검증
    checkPassword(member, password);
    memberRepository.delete(member);
  }

  // Verify
  private void verifyExistsEmail(String email) {
    memberRepository.findByEmail(email)
            .ifPresent(member -> {
              throw new VolumeTestException(ExceptionStatus.MEMBER_EMAIL_EXISTS);
            });
  }

  private Member findVerifyMemberByEmail(String email) {
    return memberRepository.findByEmail(email)
            .orElseThrow(() -> new VolumeTestException(ExceptionStatus.MEMBER_NOT_FOUND));
  }

  private Boolean checkPassword(Member member, String password) {
    // 회원의 비밀번호 추출
    String memberPassword = member.getPassword();
    // 비밀번호가 일치하지 않으면 예외 발생
    if(!passwordEncoder.matches(password, memberPassword)) {
      throw new VolumeTestException(ExceptionStatus.MEMBER_PASSWORD_MISMATCH);
    }
    // 비밀번호가 일치하면 true 반환
    return true;
  }
}
