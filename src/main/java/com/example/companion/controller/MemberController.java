package com.example.companion.controller;

import com.example.companion.command.MemberCommand;
import com.example.companion.service.MemberAutoNumService;
import com.example.companion.service.MemberInsertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
    public String form(@Validated MemberCommand memberCommand, BindingResult result){
        //오류 발생 시 오류 메세지를 html에 전달
        if(!memberCommand.ismemberPwEqualIsMemberPwCon()){
            //비밀번호와 비밀번호확인이 다른 경우에도 메세지 보내기
            //result.rejectValue(필드명, 에러코드, 메세지)
            result.rejectValue("memberPwCon", "memberCommand.memberPwCon",
                                "비밀번호가 일치하지 않습니다.");
            return "member/memberForm";
        }else{
            memberInsertService.execute(memberCommand);
            return "redirect:memberList";
        }
    }

}
