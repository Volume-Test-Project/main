package com.volumeTest.volume.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteMemberRequestDto {
    private String email;
    private String password;
}
