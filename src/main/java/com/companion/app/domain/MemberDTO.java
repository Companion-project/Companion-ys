package com.companion.app.domain;

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
    String Gender;
    String memberPhone1;
    String memberPhone2;
    String memberEmail;
    Date memberBirth;
}
