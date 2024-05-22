package com.volumeTest.volume.member.dto;


import com.volumeTest.volume.member.entity.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupResponseDto {
    private String password;
    private String email;
    private String memberName;

    public Member toEntity(){
        return Member.builder()
                .password(password)
                .email(email)
                .memberName(memberName)
                .build();
    }
}
