package com.volumeTest.volume.member.dto;

import com.volumeTest.volume.common.pattern.custom.member.Email;
import com.volumeTest.volume.common.pattern.custom.member.Name;
import com.volumeTest.volume.common.pattern.custom.member.Password;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class MemberUpdateDto {
  @Email
  private String email;
  @Password
  private String password;
  @Name
  private String name;
}
