package com.volumeTest.volume.member.mapper;

import com.volumeTest.volume.member.dto.*;
import com.volumeTest.volume.member.entity.Member;

public interface MemberMapper {
  // To Entity
  Member memberCreateDtoToMember(MemberCreateDto memberCreateDto, String encryptedPassword);

  // Response
  MemberResponseDto entityToResponse(Member member);
  MemberResponseDto memberUpdateDtoToMemberResponseDto(MemberUpdateDto memberUpdateDto);
}
