package com.example.companion.domain;

import lombok.Data;

import java.util.Date;

@Data
public class MemberDTO {
    String memberNum;
    String memberId;
    String memberPw;
    String memberPwCon;
    String memberName;
    String memberAddr;
    String memberAddrDetail;
    String memberPost;
    Date memberRegistDate;
    String Gender;
    String memberPhone1;
    String memberPhone2;
    String memberEmail;
    Date memberBirth;
    Integer point;
}
