package com.volumeTest.volume.member.dto;

import com.volumeTest.volume.member.entity.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    private String email;
    private String password;
    private String memberName;

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .password(password)
                .memberName(memberName)
                .build();
    }
}
