package com.volumeTest.volume.member.service;

import com.volumeTest.volume.member.entity.Member;

public interface MemberService {

    // 회원 생성
    Member createMember(Member member);

    // 회원 조회
    Member findMemberByEmail(String email);

    // 회원 수정
    Member updateMember(Member member, String name, String password);

    // 회원 비밀번호 변경
    Member updateMemberPassword(Member member, String password);

    // 회원 탈퇴
    void deleteMember(Member member, String password);
}
