package com.example.companion.controller;

import com.example.companion.command.MemberCommand;
import com.example.companion.service.MemberAutoNumService;
import com.example.companion.service.MemberInsertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MemberController {

    @Autowired
    MemberInsertService memberInsertService;

    @Autowired
    MemberAutoNumService memberAutoNumService;

    @RequestMapping(value = "member/memberList")
    public String list(){
        return "member/memberList";
    }

    @RequestMapping(value = "member/memberRegist", method = RequestMethod.GET)
    public String form(Model model){
        //회원번호 불러오기
        memberAutoNumService.execute(model);
        return "member/memberForm";
    }

    @RequestMapping(value = "member/memberRegist", method = RequestMethod.POST)
    public String form(MemberCommand memberCommand){
        memberInsertService.execute(memberCommand);
        return "redirect:memberList";
    }

}
