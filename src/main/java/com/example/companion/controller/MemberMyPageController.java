package com.example.companion.controller;

import com.example.companion.command.MemberCommand;
import com.example.companion.service.memberMyPage.MemberInfoUpdateService;
import com.example.companion.service.memberMyPage.MemberDropService;
import com.example.companion.service.memberMyPage.MemberInfoService;
import com.example.companion.service.memberMyPage.MemberPwModifyService;
import com.example.companion.service.memberMyPage.MyPassConfirmService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("mypage")
@Controller
public class MemberMyPageController {
    @Autowired
    MemberInfoService memberInfoService;
    @Autowired
    MemberPwModifyService memberPwModifyService;
    @Autowired
    MyPassConfirmService myPassConfirmService;
    @Autowired
    MemberDropService memberDropService;
    @Autowired
    MemberInfoUpdateService memberInfoUpdateService;

    @GetMapping("myDetail")
    public String myDetail(HttpSession session, Model model){
        memberInfoService.execute(session, model);
        return "memberShip/myInfo";
    }

    @RequestMapping(value = "userPwModify", method = RequestMethod.GET)
    public String userPwModify(){
        return "memberShip/myPwCon";
    }
    @RequestMapping(value = "memberPwModify", method = RequestMethod.POST)
    public String userPwModify(@RequestParam("memberPw") String memberPw,
                               Model model, HttpSession session){
        return memberPwModifyService.execute(session, model, memberPw);
    }

    @PostMapping("myPwUpdate")
    @ResponseBody
    public boolean myPwUpdate(@RequestParam("oldPw") String oldPw,
                              @RequestParam(value = "newPw") String newPw,
                              HttpSession session){
        return myPassConfirmService.execute(newPw, oldPw, session);
    }

    @GetMapping("memberDrop")
    public String memberDrop(){
        return "memberShip/memberDrop";
    }
    @PostMapping("memberDropOk")
    public String memberDrop(
            @RequestParam("memberPw") String memberPw, Model model,
            HttpSession session){
        int i = memberDropService.execute(memberPw, session, model);
        if(i == 1)
            return "redirect:/login/logout";
        else return "memberShip/memberDrop";
    }
    @RequestMapping(value = "memberUpdate", method = RequestMethod.GET)
    public String memberUpdate(HttpSession session, Model model){
        memberInfoService.execute(session, model);
        return "memberShip/myModify";
    }

    @RequestMapping(value = "memberUpdate", method = RequestMethod.POST)
    public String memberUpdate(@Validated MemberCommand memberCommand, BindingResult result,
                               HttpSession session){
        memberInfoUpdateService.execute(memberCommand, session, result);
        if(result.hasErrors()){
            return "memberShip/myModify";
        }
        return "redirect:myDetail";
    }
}

