package com.volumeTest.volume.member.dto;

import com.volumeTest.volume.common.pattern.custom.member.Email;
import com.volumeTest.volume.common.pattern.custom.member.Name;
import com.volumeTest.volume.common.pattern.custom.member.Password;
import com.volumeTest.volume.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class MemberResponseDto {
  private int  memberId;
  @Email
  private String email;
  @Password
  private String password;
  @Name
  private String name;

  public MemberResponseDto(Member member) {
    this.memberId = member.getMemberId();
    this.email = member.getEmail();
    this.password = member.getPassword();
    this.name = member.getName();
  }

  public MemberResponseDto(MemberUpdateDto memberUpdateDto) {
    this.email = memberUpdateDto.getEmail();
    this.password = memberUpdateDto.getPassword();
    this.name = memberUpdateDto.getName();
  }
}
