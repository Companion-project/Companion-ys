package com.example.companion.controller;

import com.example.companion.service.memberMyPage.MemberInfoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("mypage")
public class MemberMyPageController {

    @Autowired
    MemberInfoService memberInfoService;

    @GetMapping("myDetail")
    //로그인 저장한 세션값 -> 내정보를 DB에서 가져오기
    //DB에서 가져온 정보값을 model을 이용해 myInfo.html로 전송
    public String myDetail(HttpSession session, Model model){
        memberInfoService.execute(session, model);
        return "memberShip/myInfo";
    }

    @RequestMapping(value = "userPwModify", method = RequestMethod.GET)
    public String userPwModify(){
        return "membership/myPwCon";
    }

    @RequestMapping(value = "memberPwModify", method = RequestMethod.POST)
    public String userPwModify(@RequestParam("memberPw") String memberPw,
            Model model, HttpSession session){
        return null;
    }
}
