package com.companion.app.controller;

import com.companion.app.command.MemberCommand;
import com.companion.app.service.MemberInsertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MemberController {

    @Autowired
    MemberInsertService memberInsertService;

    @RequestMapping(value = "member/memberList")
    public String list(){
        return "member/memberList";
    }

    @RequestMapping(value = "member/memberRegist", method = RequestMethod.GET)
    public String form(){
        return "member/memberForm";
    }

    @RequestMapping(value = "member/memberRegist", method = RequestMethod.POST)
    public String form(MemberCommand memberCommand){
        memberInsertService.execute(memberCommand);
        return "redirect:memberList";
    }

}
