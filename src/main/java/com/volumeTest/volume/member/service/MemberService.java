package com.volumeTest.volume.member.service;


import com.volumeTest.volume.member.dto.SignupResponseDto;
import com.volumeTest.volume.member.entity.Member;
import com.volumeTest.volume.member.repository.MemberRepository;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberService {
    private final MemberRepository memberRepository;

    public Member signupMember(SignupResponseDto signupResponseDto) {
        memberRepository.findByEmail(signupResponseDto.getEmail())
                .ifPresent(member -> {
                    throw new IllegalArgumentException("이미 가입된 회원입니다.");//일단 이거 질문을 하기..!
                });

        return  memberRepository.save(signupResponseDto.toEntity());



    }
}
