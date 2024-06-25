package com.volumeTest.volume.member.service;

import com.volumeTest.volume.common.pattern.Validator.MemberValidator;
import com.volumeTest.volume.common.security.jwt.JwtProvider;
import com.volumeTest.volume.member.dto.*;
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
  private final MemberValidator memberValidator;

  @Override
  public MemberResponseDto createMember(MemberCreateDto memberCreateDto) {
    memberValidator.verifyExistsEmail(memberCreateDto.getEmail());
    // 비밀번호 암호화
    String encryptedPassword = passwordEncoder.encode(memberCreateDto.getPassword());

    Member member = memberMapper.memberCreateDtoToMember(memberCreateDto, encryptedPassword);
    member = memberRepository.save(member);
    MemberResponseDto memberCreatedResponse = memberMapper.entityToResponse(member);
    return memberCreatedResponse;
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
  public MemberResponseDto findMemberByEmail(String email) {
    Member findMember = memberValidator.findVerifyMemberByEmail(email);
    MemberResponseDto memberFindedResponse = memberMapper.entityToResponse(findMember);
    return memberFindedResponse;
  }

  @Override
  @Transactional
  public MemberResponseDto updateMember(String email, MemberUpdateDto memberUpdateDto) {
    // 회원 조회
    Member findMember = memberValidator.findVerifyMemberByEmail(email);

    memberValidator.checkPasswordForMemberUpdateAndDelete(findMember, memberUpdateDto.getPassword());

    // 변경할 정보 저장
    findMember.updateMember(memberUpdateDto);
    MemberResponseDto updatedMember = memberMapper.memberUpdateDtoToMemberResponseDto(memberUpdateDto);

    log.info("{}님의 정보가 수정되었습니다. 수정된 정보: 이름={}, 이메일={}", findMember.getName(), updatedMember.getName(), updatedMember.getEmail());
    return updatedMember;
  }


  @Override
  @Transactional
  public MemberResponseDto updateMemberPassword(String email, MemberUpdatePasswordDto memberUpdatePasswordDto) {
    Member findMember = memberValidator.findVerifyMemberByEmail(email);

    // 새로운 비밀번호와 이전 비밀번호가 같으면 변경하지 않음
    memberValidator.checkPasswordForMemberUpdatePassword(findMember, memberUpdatePasswordDto.getPassword());

    // 변경할 비밀번호 암호화
    String encryptedPassword = passwordEncoder.encode(memberUpdatePasswordDto.getPassword());

    // 변경할 암호화된 비밀번호 저장
    findMember.updateMemberPassword(encryptedPassword);

    MemberResponseDto updatePasswordResponse = memberMapper.entityToResponse(findMember);

    return updatePasswordResponse;
  }

  @Override
  @Transactional
  public void deleteMember(String email, MemberDeleteDto memberDeleteDto) {
    Member findMember = memberValidator.findVerifyMemberByEmail(email);
    // 패스워드로 검증
    memberValidator.checkPasswordForMemberUpdateAndDelete(findMember, memberDeleteDto.getPassword());

    log.info("{}님의 정보가 삭제되었습니다.", findMember.getEmail());
    memberRepository.delete(findMember);
  }
}
