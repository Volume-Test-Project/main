package com.volumeTest.volume.member.service;

import com.volumeTest.volume.common.exception.ExceptionStatus;
import com.volumeTest.volume.common.pattern.Validator.MemberValidator;
import com.volumeTest.volume.member.dto.MemberDto;
import com.volumeTest.volume.member.entity.Member;
import com.volumeTest.volume.member.mapper.MemberMapper;
import com.volumeTest.volume.member.repository.MemberRepository;
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
  private final MemberValidator memberValidator;

  @Override
  public MemberDto.MemberResponse createMember(MemberDto.Post memberPostDto) {
    memberValidator.verifyExistsEmail(memberPostDto.getEmail());
    // 비밀번호 암호화
    String encryptedPassword = passwordEncoder.encode(memberPostDto.getPassword());

    Member member = memberMapper.memberPostDtoToMember(memberPostDto, encryptedPassword);
    member = memberRepository.save(member);
    MemberDto.MemberResponse memberCreatedResponse = memberMapper.entityToResponse(member);
    return memberCreatedResponse;
  }

  @Override
  public MemberDto.MemberResponse findMemberByEmail(String email) {
    Member findMember = memberValidator.findVerifyMemberByEmail(email);
    MemberDto.MemberResponse memberFindedResponse = memberMapper.entityToResponse(findMember);
    return memberFindedResponse;
  }

  @Override
  public MemberDto.MemberResponse updateMember(String email, MemberDto.Put memberPutDto) {
    // 회원 조회
    Member findMember = memberValidator.findVerifyMemberByEmail(email);

    memberValidator.checkPassword(findMember, memberPutDto.getPassword());

    // 변경할 정보 저장
    Member updatedMember = memberMapper.memberPutDtoToMember(memberPutDto);

    // 변경된 정보 저장
    memberRepository.save(updatedMember);

    MemberDto.MemberPutResponse memberUpdateResponse = memberMapper.memberToMemberPutResponseDto(updatedMember);

    log.info("{}님의 정보가 수정되었습니다. 수정된 정보: 이름={}, 이메일={}", findMember.getName(), updatedMember.getName(), updatedMember.getEmail());
    return memberUpdateResponse;
  }

  @Override
  public MemberDto.MemberResponse updateMemberPassword(String email, MemberDto.PutPassword memberPutPasswordDto) {
    Member findMember = memberValidator.findVerifyMemberByEmail(email);

    // 새로운 비밀번호와 이전 비밀번호가 같으면 변경하지 않음
    memberValidator.verifyPrePasswordAndNewPasswordMatch(passwordEncoder.matches(memberPutPasswordDto.getPassword(), findMember.getPassword()), ExceptionStatus.MEMBER_PASSWORD_NOT_CHANGE);

    // 변경할 비밀번호 암호화
    String encryptedPassword = passwordEncoder.encode(memberPutPasswordDto.getPassword());

    // 변경할 암호화된 비밀번호 저장
    Member updatedMember = rebuildMemberPassword(findMember, encryptedPassword);

    // 변경된 정보 저장
    updatedMember = memberRepository.save(updatedMember);

    MemberDto.MemberResponse updatePasswordResponse = memberMapper.entityToResponse(updatedMember);

    return updatePasswordResponse;
  }

  @Override
  public void deleteMember(String email, MemberDto.Delete memberDeleteDto) {
    Member findMember = memberValidator.findVerifyMemberByEmail(email);
    // 패스워드로 검증
    String encryptedPassword = passwordEncoder.encode(memberDeleteDto.getPassword());
    memberValidator.checkPassword(findMember, encryptedPassword);

    log.info("{}님의 정보가 삭제되었습니다.", findMember.getEmail());
    memberRepository.delete(findMember);
  }
  
  private Member rebuildMemberPassword(Member member, String encryptedPassword) {
    return Member.builder()
            .memberId(member.getMemberId())
            .email(member.getEmail())
            .password(encryptedPassword)
            .name(member.getName())
            .build();
  }
}
