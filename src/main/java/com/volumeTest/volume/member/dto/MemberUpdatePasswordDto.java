package com.volumeTest.volume.member.dto;

import com.volumeTest.volume.common.pattern.custom.member.Email;
import com.volumeTest.volume.common.pattern.custom.member.Password;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class MemberUpdatePasswordDto {
  @Email
  private String email;
  @Password
  private String password;
}
