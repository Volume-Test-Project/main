package com.volumeTest.volume.member.service;

import com.volumeTest.volume.member.dto.*;

public interface MemberService {

    // 회원 생성
    MemberResponseDto createMember(MemberCreateDto memberCreateDto);

    // 회원 조회
    MemberResponseDto findMemberByEmail(String email);

    // 회원 수정
    MemberResponseDto updateMember(String email, MemberUpdateDto memberUpdateDto);

    // 회원 비밀번호 변경
    MemberResponseDto updateMemberPassword(String email, MemberUpdatePasswordDto memberUpdatePasswordDto);

    // 회원 탈퇴
    void deleteMember(String email, MemberDeleteDto memberDeleteDto);
}
