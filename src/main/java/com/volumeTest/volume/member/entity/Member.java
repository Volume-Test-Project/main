package com.volumeTest.volume.member.entity;

import com.volumeTest.volume.member.dto.MemberUpdateDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MEMBERS")
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int memberId;

  @Column(nullable = false, unique = true, updatable = false, length = 30)
  private String email;

  @Column(nullable = false, unique = false, updatable = true)
  private String password;

  @Column(nullable = false, unique = false, updatable = true, length = 20)
  private String name;

  public void updateMember(MemberUpdateDto memberUpdateDto) {
    this.name = memberUpdateDto.getName();
  }

  public void updateMemberPassword(String password) {
    this.password = password;
  }
}
