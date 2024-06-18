package com.volumeTest.volume.member.dto;

import com.volumeTest.volume.common.pattern.custom.member.Email;
import com.volumeTest.volume.common.pattern.custom.member.Password;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberLoginDto {
  @Email
  private String email;
  @Password
  private String password;
}