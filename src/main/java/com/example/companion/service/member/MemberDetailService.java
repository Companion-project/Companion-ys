package com.example.companion.service.member;

import com.example.companion.command.MemberCommand;
import com.example.companion.domain.MemberDTO;
import com.example.companion.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class MemberDetailService {
    @Autowired
    MemberMapper memberMapper;

    public void execute(String memberNum, Model model){
        MemberDTO dto = memberMapper.memberSelectOne(memberNum);
        MemberCommand memberCommand = new MemberCommand();
        memberCommand.setMemberAddr(dto.getMemberAddr());
        memberCommand.setMemberAddr2(dto.getMemberAddrDetail());
        memberCommand.setMemberBirth(dto.getMemberBirth());
        memberCommand.setMemberEmail(dto.getMemberEmail());
        memberCommand.setMemberGender(dto.getGender());
        memberCommand.setMemberId(dto.getMemberId());
        memberCommand.setMemberName(dto.getMemberName());
        memberCommand.setMemberNum(dto.getMemberNum());
        memberCommand.setMemberPhone1(dto.getMemberPhone1());
        memberCommand.setMemberPhone2(dto.getMemberPhone2());
        memberCommand.setMemberPost(dto.getMemberPost());
        memberCommand.setMemberRegiDate(dto.getMemberRegistDate());
        memberCommand.setPoint(dto.getPoint());
        model.addAttribute("memberCommand", memberCommand);

    }

}
