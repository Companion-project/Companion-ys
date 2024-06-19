package com.example.companion.controller;

import com.example.companion.command.MemberCommand;
import com.example.companion.service.member.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("member")
public class MemberController {

    @Autowired
    MemberInsertService memberInsertService;

    @Autowired
    MemberAutoNumService memberAutoNumService;

    @Autowired
    MemberListService memberListService;

    @Autowired
    MembersDeleteService membersDeleteService;

    @Autowired
    MemberDetailService memberDetailService;

    @Autowired
    MemberUpdateService memberUpdateService;

    @Autowired
    MemberDeleteService memberDeleteService;

    @GetMapping("memberdelete/{memberNum}")
    public String memberdelete(
            @PathVariable(value="memberNum") String memberNum) {
        memberDeleteService.execute(memberNum);
        return "redirect:../memberList"; // 주소 전달 방식이라 ../를 해줘야 합니다. 그렇지 않으면
        // 이상 마치겠습니다.
    }

    @PostMapping("memberModify")
    public String memberModify(@Validated MemberCommand memberCommand, BindingResult result){
        if(result.hasErrors()){
            return "member/memberModify";
        }
        memberUpdateService.execute(memberCommand);
        return "redirect:memberDetail?memberNum="+memberCommand.getMemberNum();
    }

    @RequestMapping("memberUpdate")
    public String memberUpdate(@RequestParam(value = "memberNum") String memberNum, Model model){
        memberDetailService.execute(memberNum, model);
        return "member/memberModify";
    }

    @GetMapping("memberDetail")
    public String memberDetail(@RequestParam(value="memberNum") String memberNum, Model model){
        memberDetailService.execute(memberNum, model);
        return "member/memberInfo";
    }

    @PostMapping("membersDelete")
    public String dels(@RequestParam(value = "memDels") String memDels[]){
        membersDeleteService.execute(memDels);
        return "redirect:memberList";
    }

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
