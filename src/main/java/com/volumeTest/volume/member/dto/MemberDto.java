package com.volumeTest.volume.member.dto;

import com.volumeTest.volume.common.pattern.custom.member.Email;
import com.volumeTest.volume.common.pattern.custom.member.Name;
import com.volumeTest.volume.common.pattern.custom.member.Password;
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
    public static class Patch {

      @Password
      private String password;
      @Name
      private String name;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class CheckPassword {
      @Password
      private String password;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public abstract static class MemberResponse {

      private int  memberId;
      @Email
      private String email;
      @Password
      private String password;
      @Name
      private String name;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberPostResponse extends MemberResponse {
      private LocalDateTime createdAt;

      public MemberPostResponse(int memberId, String email, String password, String name, LocalDateTime createdAt) {
        super(memberId, email, password, name);
        this.createdAt = createdAt;
      }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberPatchResponse extends MemberResponse {
      private LocalDateTime modifiedAt;

      public MemberPatchResponse(int memberId, String email, String password, String name, LocalDateTime modifiedAt) {
        super(memberId, email, password, name);
        this.modifiedAt = modifiedAt;
      }
    }

  }