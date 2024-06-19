package com.example.companion.service.memberRegister;

import com.example.companion.command.MemberCommand;
import com.example.companion.domain.MemberDTO;
import com.example.companion.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Date;

@Service
public class UserWriteService {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserMapper userMapper;

    public void execute(MemberCommand memberCommand, Model model){
        String memberId = memberCommand.getMemberId();
        String memberPw = memberCommand.getMemberPw();
        String memberName = memberCommand.getMemberName();
        String memberPhone1 = memberCommand.getMemberPhone1();
        String memberPhone2 = memberCommand.getMemberPhone2();
        String memberAddr = memberCommand.getMemberAddr();
        String memberAddrDetail = memberCommand.getMemberAddr2();
        String memberPost = memberCommand.getMemberPost();
        String memberEmail = memberCommand.getMemberEmail();
        Date memberBirth = memberCommand.getMemberBirth();
        String memberGender = memberCommand.getMemberGender();

        MemberDTO dto = new MemberDTO();
        dto.setMemberAddr(memberAddr);
        dto.setMemberAddrDetail(memberAddrDetail);
        dto.setMemberEmail(memberEmail);
        dto.setGender(memberGender);
        dto.setMemberId(memberId);
        dto.setMemberName(memberName);
        dto.setMemberPhone1(memberPhone1);
        dto.setMemberPhone2(memberPhone2);
        dto.setMemberPost(memberPost);
        dto.setMemberPw(passwordEncoder.encode(memberPw));//비밀번호 암호화
        dto.setMemberBirth(memberBirth);

        userMapper.userInsert(dto);
    }
}
