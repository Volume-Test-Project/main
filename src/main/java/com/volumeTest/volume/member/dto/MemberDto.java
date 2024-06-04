package com.volumeTest.volume.member.dto;

import com.volumeTest.volume.common.pattern.custom.member.Email;
import com.volumeTest.volume.common.pattern.custom.member.Name;
import com.volumeTest.volume.common.pattern.custom.member.Password;
import com.volumeTest.volume.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class MemberDto {

  @Getter
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Login {

    @Email
    private String email;
    @Password
    private String password;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class Post {

      @Email
      private String email;
      @Password
      private String password;
      @Name
      private String name;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class Put {

      @Email
      private String email;
      @Password
      private String password;
      @Name
      private String name;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class PutPassword {

        @Email
        private String email;
        @Password
        private String password;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class CheckPassword {
      @Password
      private String password;
    }

    @Getter
    @AllArgsConstructor
    public static class Delete {

      @Email
      private String email;
      @Password
      private String password;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MemberResponse {

      private int  memberId;
      @Email
      private String email;
      @Password
      private String password;
      @Name
      private String name;

      public MemberResponse(Member member) {
        this.memberId = member.getMemberId();
        this.email = member.getEmail();
        this.password = member.getPassword();
        this.name = member.getName();
      }
    }

    @Getter
    @NoArgsConstructor
    public static class MemberPostResponse extends MemberResponse {
      private LocalDateTime createdAt;

      public MemberPostResponse(Member member, LocalDateTime createdAt) {
        super(member.getMemberId(), member.getEmail(), member.getPassword(), member.getName());
        this.createdAt = createdAt;
      }
    }

    @Getter
    @NoArgsConstructor
    public static class MemberPutResponse extends MemberResponse {
      private LocalDateTime modifiedAt;

      public MemberPutResponse(Member member, LocalDateTime modifiedAt) {
        super(member.getMemberId(), member.getEmail(), member.getPassword(), member.getName());
        this.modifiedAt = modifiedAt;
      }
    }

  }