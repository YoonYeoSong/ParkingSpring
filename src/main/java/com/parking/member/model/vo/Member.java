package com.parking.member.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor // 기본생성자도 추가
@AllArgsConstructor
@Data
public class Member {
  private String userCode;
  private String userEmail;
  private String userPw;
  private String userPhone;
  private String userName;
  private String userAddr;
  private Date userCreatedDate;
  private Date userLoginDate;
  private int userSmsYn;
  private int userEmailYn;
  private int userEmailVerified;
  private String userSnsAccount;
  private String userOriginalFilename;
  private String userRenamedFilename;
}