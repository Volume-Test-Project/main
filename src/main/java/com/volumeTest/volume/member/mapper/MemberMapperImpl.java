package com.volumeTest.volume.member.mapper;

import com.volumeTest.volume.common.pattern.Validator.MemberValidator;
import com.volumeTest.volume.member.dto.*;
import com.volumeTest.volume.member.entity.Member;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;

import com.volumeTest.volume.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2024-05-20T15:13:18+0900",
        comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
@RequiredArgsConstructor
public class MemberMapperImpl implements MemberMapper {

  private final MemberRepository memberRepository;
  private final MemberValidator memberValidator;

  @Override
  public Member memberCreateDtoToMember(MemberCreateDto memberCreateDto, String encryptedPassword) {
    if ( memberCreateDto == null ) {
      return null;
    }

    return Member.builder()
            .email(memberCreateDto.getEmail())
            .password(encryptedPassword)
            .name(memberCreateDto.getName())
            .build();
  }


  @Override
  public Member memberUpdateDtoToMember(MemberUpdateDto memberUpdateDto) {
    if (memberUpdateDto == null) {
      return null;
    }
    // 회원 조회
    Member findMember = memberValidator.findVerifyMemberByEmail(memberUpdateDto.getEmail());

    // Member 객체를 복사하고 변경할 필드만 수정
    return Member.builder()
            .memberId(findMember.getMemberId()) // 기존 ID 유지
            .email(findMember.getEmail())       // 기존 이메일 유지 (변경 불가)
            .password(findMember.getPassword()) // 새 비밀번호 설정
            .name(memberUpdateDto.getName())    // 새 이름 설정
            .build();
  }

  @Override
  public MemberLoginDto memberToLoginDto(Member member) {
    if ( member == null ) {
      return null;
    }

    MemberLoginDto login = new MemberLoginDto();

    return login;
  }

  @Override
  public MemberCheckPasswordDto checkPasswordToMember(MemberCheckPasswordDto checkPasswordDto) {
    if ( checkPasswordDto == null ) {
      return null;
    }
    String password = null;
    password = checkPasswordDto.getPassword();
    MemberCheckPasswordDto checkPassword = new MemberCheckPasswordDto(password);

    return checkPassword;
  }

  @Override
  public MemberResponseDto entityToResponse(Member member) {
    if ( member == null ) {
      return null;
    }

    int memberId = 0;
    String email = null;
    String password = null;
    String name = null;
    LocalDateTime createdAt = null;
    LocalDateTime modifiedAt = null;

    MemberResponseDto memberResponse = new MemberResponseDto(member);
    return memberResponse;
  }

  @Override
  public MemberCreateResponseDto memberToMemberPostResponseDto(Member member) {
    if ( member == null ) {
      return null;
    }

    int memberId = 0;
    String email = null;
    String password = null;
    String name = null;
    LocalDateTime createdAt = null;

    MemberCreateResponseDto memberPostResponse = new MemberCreateResponseDto(member, createdAt );

    return memberPostResponse;
  }

  @Override
  public MemberUpdateResponseDto memberToMemberPutResponseDto(Member member) {
    if ( member == null ) {
      return null;
    }

    int memberId = 0;
    String email = null;
    String password = null;
    String name = null;
    LocalDateTime modifiedAt = null;

    MemberUpdateResponseDto memberPutResponse = new MemberUpdateResponseDto(member, modifiedAt );

    return memberPutResponse;
  }
}
