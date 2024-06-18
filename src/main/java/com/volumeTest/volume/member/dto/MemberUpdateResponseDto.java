package com.volumeTest.volume.member.dto;

import com.volumeTest.volume.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MemberUpdateResponseDto extends MemberResponseDto {
  private LocalDateTime modifiedAt;

  public MemberUpdateResponseDto(Member member, LocalDateTime modifiedAt) {
    super(member.getMemberId(), member.getEmail(), member.getPassword(), member.getName());
    this.modifiedAt = modifiedAt;
  }
}
