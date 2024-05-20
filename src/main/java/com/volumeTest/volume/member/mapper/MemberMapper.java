package com.volumeTest.volume.member.mapper;

import com.volumeTest.volume.member.dto.MemberDto;
import com.volumeTest.volume.member.entity.Member;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {
  // To Entity
  Member memberPostDtoToMember(MemberDto.Post memberPostDto);
  Member memberPatchDtoToMember(MemberDto.Patch memberPatchDto);

  // Login
  MemberDto.Login memberToLoginDto(Member member);

  // Check
  MemberDto.CheckPassword checkPasswordToMember(MemberDto.CheckPassword checkPasswordDto);

  // Response
  MemberDto.MemberPostResponse memberToMemberPostResponseDto(Member member);
  MemberDto.MemberPatchResponse memberToMemberPatchResponseDto(Member member);
}
