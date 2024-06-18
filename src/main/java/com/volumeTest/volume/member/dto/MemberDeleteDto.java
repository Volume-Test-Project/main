package com.volumeTest.volume.member.dto;

import com.volumeTest.volume.common.pattern.custom.member.Email;
import com.volumeTest.volume.common.pattern.custom.member.Password;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberDeleteDto {
  @Email
  private String email;
  @Password
  private String password;
}
