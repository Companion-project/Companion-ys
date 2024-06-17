package com.example.companion.command;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
    //문자형 날짜 자동형변환을 위한 패턴 필요
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date memberBirth;
    //나중에 사용할 멤버필드 추가
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date memberRegiDate;

    Integer point;
    //비밀번호와 비밀번호 확인이 같은지 확인을 위한 메서드
    public boolean ismemberPwEqualIsMemberPwCon(){
        return memberPw.equals(memberPwCon);
    }
}
