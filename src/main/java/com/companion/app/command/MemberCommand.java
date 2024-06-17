package com.companion.app.command;

import lombok.Data;

import java.util.Date;

@Data
public class MemberCommand {
    String memberNum;
    String memberId;
    String memberPw;
    String memberPwCon;
    String memberName;
    String memberAddr;
    String memberAddr2;
    String memberPost;
    String memberGender;
    String memberPhone1;
    String memberPhone2;
    String memberEmail;
    Date memberBirth;
}
