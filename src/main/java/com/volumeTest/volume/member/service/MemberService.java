package com.volumeTest.volume.member.service;

import com.volumeTest.volume.member.dto.MemberDto;
import com.volumeTest.volume.member.entity.Member;

public interface MemberService {

    // 회원 생성
    MemberDto.MemberResponse createMember(MemberDto.Post memberPostDto);

    // 회원 조회
    MemberDto.MemberResponse findMemberByEmail(String email);

    // 회원 수정
    MemberDto.MemberResponse updateMember(String email, MemberDto.Put memberPutDto);

    // 회원 비밀번호 변경
    MemberDto.MemberResponse updateMemberPassword(String email, MemberDto.PutPassword memberPutPasswordDto);

    // 회원 탈퇴
    void deleteMember(String email, MemberDto.Delete memberDeleteDto);
}
