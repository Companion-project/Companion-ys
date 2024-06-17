package com.companion.app.service;

import com.companion.app.command.MemberCommand;
import com.companion.app.domain.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberInsertService {
    @Autowired
    PasswordEncoder passwordEncoder;
    public void execute(MemberCommand memberCommand){
    MemberDTO dto = new MemberDTO();
    dto.setGender(memberCommand.getMemberGender());
    dto.setMemberAddr(memberCommand.getMemberAddr());
    dto.setMemberAddrDetail(memberCommand.getMemberAddr2());
    dto.setMemberBirth(memberCommand.getMemberBirth());
    dto.setMemberEmail(memberCommand.getMemberEmail());
    dto.setMemberId(memberCommand.getMemberId());
    dto.setMemberName(memberCommand.getMemberName());
    dto.setMemberNum(memberCommand.getMemberNum());
    dto.setMemberPhone1(memberCommand.getMemberPhone1());
    dto.setMemberPhone2(memberCommand.getMemberPhone2());
    dto.setMemberPost(memberCommand.getMemberPost());
    //비밀번호 암호화
    dto.setMemberPw(passwordEncoder.encode(memberCommand.getMemberPw()));
    }
}
