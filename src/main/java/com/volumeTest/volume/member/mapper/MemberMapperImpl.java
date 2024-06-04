package com.volumeTest.volume.member.mapper;

import com.volumeTest.volume.common.pattern.Validator.MemberValidator;
import com.volumeTest.volume.member.dto.MemberDto;
import com.volumeTest.volume.member.entity.Member;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;

import com.volumeTest.volume.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
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
  public Member memberPostDtoToMember(MemberDto.Post memberPostDto, String encryptedPassword) {
    if ( memberPostDto == null ) {
      return null;
    }

    return Member.builder()
            .email(memberPostDto.getEmail())
            .password(encryptedPassword)
            .name(memberPostDto.getName())
            .build();
  }


  @Override
  public Member memberPutDtoToMember(MemberDto.Put memberPutDto) {
    if (memberPutDto == null) {
      return null;
    }
    // 회원 조회
    Member findMember = memberValidator.findVerifyMemberByEmail(memberPutDto.getEmail());

    // Member 객체를 복사하고 변경할 필드만 수정
    return Member.builder()
            .memberId(findMember.getMemberId()) // 기존 ID 유지
            .email(findMember.getEmail())       // 기존 이메일 유지 (변경 불가)
            .password(findMember.getPassword()) // 새 비밀번호 설정
            .name(memberPutDto.getName())    // 새 이름 설정
            .build();
  }

  @Override
  public MemberDto.Login memberToLoginDto(Member member) {
    if ( member == null ) {
      return null;
    }

    MemberDto.Login login = new MemberDto.Login();

    return login;
  }

  @Override
  public MemberDto.CheckPassword checkPasswordToMember(MemberDto.CheckPassword checkPasswordDto) {
    if ( checkPasswordDto == null ) {
      return null;
    }
    String password = null;
    password = checkPasswordDto.getPassword();
    MemberDto.CheckPassword checkPassword = new MemberDto.CheckPassword(password);

    return checkPassword;
  }

  @Override
  public MemberDto.MemberResponse entityToResponse(Member member) {
    if ( member == null ) {
      return null;
    }

    int memberId = 0;
    String email = null;
    String password = null;
    String name = null;
    LocalDateTime createdAt = null;
    LocalDateTime modifiedAt = null;

    MemberDto.MemberResponse memberResponse = new MemberDto.MemberResponse(member);
    return memberResponse;
  }

  @Override
  public MemberDto.MemberPostResponse memberToMemberPostResponseDto(Member member) {
    if ( member == null ) {
      return null;
    }

    int memberId = 0;
    String email = null;
    String password = null;
    String name = null;
    LocalDateTime createdAt = null;

    MemberDto.MemberPostResponse memberPostResponse = new MemberDto.MemberPostResponse(member, createdAt );

    return memberPostResponse;
  }

  @Override
  public MemberDto.MemberPutResponse memberToMemberPutResponseDto(Member member) {
    if ( member == null ) {
      return null;
    }

    int memberId = 0;
    String email = null;
    String password = null;
    String name = null;
    LocalDateTime modifiedAt = null;

    MemberDto.MemberPutResponse memberPutResponse = new MemberDto.MemberPutResponse(member, modifiedAt );

    return memberPutResponse;
  }
}
