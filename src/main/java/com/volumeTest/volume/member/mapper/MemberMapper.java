package com.volumeTest.volume.member.mapper;

import com.volumeTest.volume.member.dto.MemberDto;
import com.volumeTest.volume.member.entity.Member;

public interface MemberMapper {
  // To Entity
  Member memberPostDtoToMember(MemberDto.Post memberPostDto, String encryptedPassword);
  Member memberPutDtoToMember(MemberDto.Put memberPutDto);

  // Login
  MemberDto.Login memberToLoginDto(Member member);

  // Check
  MemberDto.CheckPassword checkPasswordToMember(MemberDto.CheckPassword checkPasswordDto);

  // Response
  MemberDto.MemberResponse entityToResponse(Member member);
  MemberDto.MemberPostResponse memberToMemberPostResponseDto(Member member);
  MemberDto.MemberPutResponse memberToMemberPutResponseDto(Member member);
}
