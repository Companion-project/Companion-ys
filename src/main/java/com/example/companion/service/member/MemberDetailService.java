package com.example.companion.service.member;

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

    }
}
