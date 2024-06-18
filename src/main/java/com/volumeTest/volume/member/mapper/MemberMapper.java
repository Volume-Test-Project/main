package com.volumeTest.volume.member.mapper;

import com.volumeTest.volume.member.dto.*;
import com.volumeTest.volume.member.entity.Member;

public interface MemberMapper {
  // To Entity
  Member memberCreateDtoToMember(MemberCreateDto memberCreateDto, String encryptedPassword);
  Member memberUpdateDtoToMember(MemberUpdateDto memberUpdateDto);

  // Login
  MemberLoginDto memberToLoginDto(Member member);

  // Check
  MemberCheckPasswordDto checkPasswordToMember(MemberCheckPasswordDto checkPasswordDto);

  // Response
  MemberResponseDto entityToResponse(Member member);
  MemberCreateResponseDto memberToMemberPostResponseDto(Member member);
  MemberUpdateResponseDto memberToMemberPutResponseDto(Member member);
}
