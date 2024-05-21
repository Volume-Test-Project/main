package com.volumeTest.volume.member.service;

import com.volumeTest.volume.member.dto.DeleteMemberRequestDto;
import com.volumeTest.volume.member.dto.SignupRequestDto;
import com.volumeTest.volume.member.entity.Member;
import com.volumeTest.volume.member.repository.MemberRepository;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Getter
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public Member saveMember(SignupRequestDto requestDto) {
        memberRepository.findByEmail(requestDto.getEmail())
                .ifPresent(member -> {
                    throw new IllegalArgumentException("이미 가입된 회원입니다.");
                });

        return memberRepository.save(requestDto.toEntity());
        // 회원 가입 로직
    }

    public void deleteMember(DeleteMemberRequestDto requestDto) {
        // 회원 탈퇴 로직
        Member member = memberRepository.findByEmail(requestDto.getEmail()).orElseThrow();
        if (member.getPassword().equals(requestDto.getPassword())) {
            memberRepository.delete(member);
        } else {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }

    public List<Member> getMemberList() {
        // 회원 목록 조회 로직
        return memberRepository.findAll();
    }
}
