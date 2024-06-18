package com.example.companion.controller;

import com.example.companion.command.MemberCommand;
import com.example.companion.service.member.MemberAutoNumService;
import com.example.companion.service.member.MemberInsertService;
import com.example.companion.service.member.MemberListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("member")
public class MemberController {

    @Autowired
    MemberInsertService memberInsertService;

    @Autowired
    MemberAutoNumService memberAutoNumService;

    @Autowired
    MemberListService memberListService;

    @RequestMapping(value = "memberList")
    public String list(
            //처음 페이지 열릴 때는 searchWord가 없으므로 페이지 오류 발생
            //오류 방지를 위해 필수가 아니라고 전달
            @RequestParam(value = "searchWord", required = false) String searchWord,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            Model model){
        //회원들의 정보를 담아 memberList.html에 보낼 수 있게 Model이 필요
        memberListService.execute(model, searchWord, page);

        return "member/memberList";
    }

    @RequestMapping(value = "memberRegist", method = RequestMethod.GET)
    public String form(Model model){
        //회원번호 불러오기
        memberAutoNumService.execute(model);
        return "member/memberForm";
    }

    @RequestMapping(value = "memberRegist", method = RequestMethod.POST)
    public String form(@Validated MemberCommand memberCommand, BindingResult result){
        //오류 발생 시 오류 메세지를 html에 전달
        if(result.hasErrors()){
            return "member/memberForm";
        }
        if(!memberCommand.isMemberPwEqualsMemberPwCon()){
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
