package com.volumeTest.volume.member.mapper;

import com.volumeTest.volume.member.dto.MemberDto;
import com.volumeTest.volume.member.entity.Member;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2024-05-20T15:13:18+0900",
        comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
public class MemberMapperImpl implements MemberMapper {

  @Override
  public Member memberPostDtoToMember(MemberDto.Post memberPostDto) {
    if ( memberPostDto == null ) {
      return null;
    }

    Member member = new Member();

    return member;
  }

  @Override
  public Member memberPatchDtoToMember(MemberDto.Patch memberPatchDto) {
    if ( memberPatchDto == null ) {
      return null;
    }

    Member member = new Member();

    return member;
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
  public MemberDto.MemberPostResponse memberToMemberPostResponseDto(Member member) {
    if ( member == null ) {
      return null;
    }

    int memberId = 0;
    String email = null;
    String password = null;
    String name = null;
    LocalDateTime createdAt = null;

    MemberDto.MemberPostResponse memberPostResponse = new MemberDto.MemberPostResponse( memberId, email, password, name, createdAt );

    return memberPostResponse;
  }

  @Override
  public MemberDto.MemberPatchResponse memberToMemberPatchResponseDto(Member member) {
    if ( member == null ) {
      return null;
    }

    int memberId = 0;
    String email = null;
    String password = null;
    String name = null;
    LocalDateTime modifiedAt = null;

    MemberDto.MemberPatchResponse memberPatchResponse = new MemberDto.MemberPatchResponse( memberId, email, password, name, modifiedAt );

    return memberPatchResponse;
  }
}
