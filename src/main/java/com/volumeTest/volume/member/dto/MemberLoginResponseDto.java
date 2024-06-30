package com.volumeTest.volume.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class MemberLoginResponseDto {
    private String accessToken;
    private String refreshToken;
}
