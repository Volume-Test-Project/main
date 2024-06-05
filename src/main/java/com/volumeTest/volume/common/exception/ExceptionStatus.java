package com.volumeTest.volume.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ExceptionStatus {

  // Member
  MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, 10001, "사용자를 찾을 수 없습니다."),
  MEMBER_EXISTS(HttpStatus.CONFLICT, 10002, "이미 존재하는 사용자입니다."),
  MEMBER_EMAIL_EXISTS(HttpStatus.CONFLICT, 10003, "이미 존재하는 Email 입니다."),

  // Verify
  MEMBER_PASSWORD_MISMATCH(HttpStatus.CONFLICT, 10201, "비밀번호가 일치하지 않습니다."),
  MEMBER_PASSWORD_NOT_CHANGE(HttpStatus.CONFLICT, 10202, "기존의 비밀번호와 같습니다. 비밀번호가 변경되지 않았습니다."),

  // Login
  BAD_CREDENTIALS(HttpStatus.UNAUTHORIZED, 10301, "아이디 또는 비밀번호가 일치하지 않습니다."),
  AUTHENTICATION_CREDENTIALS_NOT_FOUND(HttpStatus.UNAUTHORIZED, 10302, "인증 정보가 없습니다."),
  UNKNOWN_LOGIN_FAIL(HttpStatus.UNAUTHORIZED, 10302, "알 수 없는 로그인 오류입니다."),

  //concert
  CONCERT_NOT_FOUND(HttpStatus.NOT_FOUND, 20001, "콘서트를 찾을 수 없습니다.");

  private final int status;
  private final int customCode;
  private final String message;
  private final String err;

  ExceptionStatus(HttpStatus httpStatus, int customCode, String message) {
    this.status = httpStatus.value();
    this.customCode = customCode;
    this.message = message;
    this.err = httpStatus.getReasonPhrase();
  }
}