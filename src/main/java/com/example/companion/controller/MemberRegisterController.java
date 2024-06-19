package com.example.companion.controller;

import com.example.companion.command.MemberCommand;
import com.example.companion.service.memberRegister.UserWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Member;

@Controller
@RequestMapping("register")
public class MemberRegisterController {

    @Autowired
    UserWriteService userWriteService;

    /*모든 주소에서 memberCommand를 사용할 수 있도록 정의*/
    @ModelAttribute
    public MemberCommand memberCommand(){
        return new MemberCommand();
    }
    
    @RequestMapping(value = "userAgree", method = RequestMethod.GET)
    public String agree(){
        return "memberRegist/userAgree";
    }

    @RequestMapping(value="userWrite", method = RequestMethod.POST)
    public String userWrite(
        @RequestParam(value="agree", defaultValue = "false") boolean agree){
        //동의하지 않아도 넘어가는 경우를 차단
        if(agree==false){
            return "memberRegist/userAgree";
        }
        return "memberRegist/userForm";
    }
    @PostMapping("userRegist")
    public String userRegist(MemberCommand memberCommand, Model model, BindingResult result){
        if(result.hasErrors()){
            return "membeRegist/userForm";
        }
        userWriteService.execute(memberCommand, model);
        return "memberRegist/welcome";
    }
}
