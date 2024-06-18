package com.example.companion.service.member;

import com.example.companion.domain.MemberDTO;
import com.example.companion.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class MemberListService {

    @Autowired
    MemberMapper memberMapper;

    public void execute(Model model, String searchWord){
        List<MemberDTO> list = memberMapper.selectAll(searchWord);
        model.addAttribute("dtos", list);
        model.addAttribute("searchWord", searchWord);

    }
}
