package com.volumeTest.volume.member.dto;

import com.volumeTest.volume.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MemberCreateResponseDto extends MemberResponseDto {
  private LocalDateTime createdAt;

  public MemberCreateResponseDto(Member member, LocalDateTime createdAt) {
    super(member.getMemberId(), member.getEmail(), member.getPassword(), member.getName());
    this.createdAt = createdAt;
  }
}
