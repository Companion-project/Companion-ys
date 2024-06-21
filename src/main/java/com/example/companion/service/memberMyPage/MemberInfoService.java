package com.example.companion.service.memberMyPage;

import com.example.companion.command.MemberCommand;
import com.example.companion.domain.AuthInfoDTO;
import com.example.companion.domain.MemberDTO;
import com.example.companion.mapper.MemberMapper;
import com.example.companion.mapper.MemberMyMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class MemberInfoService {
    @Autowired
    MemberMyMapper memberMyMapper;

    public void execute(HttpSession session, Model model){
        AuthInfoDTO authInfo = (AuthInfoDTO) session.getAttribute("auth");
        String memberId = authInfo.getUserId();

        MemberDTO dto = memberMyMapper.memberInfo(memberId);

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
