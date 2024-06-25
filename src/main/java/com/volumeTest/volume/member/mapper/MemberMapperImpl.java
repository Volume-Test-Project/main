package com.volumeTest.volume.member.mapper;

import com.volumeTest.volume.member.dto.*;
import com.volumeTest.volume.member.entity.Member;
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
  public MemberResponseDto memberUpdateDtoToMemberResponseDto(MemberUpdateDto memberUpdateDto)  {
    if ( memberUpdateDto == null ) {
      return null;
    }

    MemberResponseDto memberResponse = new MemberResponseDto(memberUpdateDto);

    return memberResponse;
  }

  @Override
  public MemberResponseDto entityToResponse(Member member) {
    if ( member == null ) {
      return null;
    }

    MemberResponseDto memberResponse = new MemberResponseDto(member);
    return memberResponse;
  }
}
