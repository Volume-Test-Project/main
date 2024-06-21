package com.volumeTest.volume.member.dto;

import com.volumeTest.volume.common.pattern.custom.member.Password;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberCheckPasswordDto {
  @Password
  private String password;
}
