package com.volumeTest.volume.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberLoginRequestDto {
    private String email;
    private String password;
}
