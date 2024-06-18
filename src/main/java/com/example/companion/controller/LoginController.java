package com.example.companion.controller;

import com.example.companion.service.login.IdcheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("login")
public class LoginController {
    @Autowired
    IdcheckService idcheckService;
    @PostMapping("userIdCheck")
    //html문서가 아닌 텍스트를 전달하기 위해서는 @ResponseBody필요
    public @ResponseBody String userIdCheck(
           @RequestParam(value = "userId") String userId ){
        String resultId = idcheckService.execute(userId);
        if(resultId == null){
            return "사용가능한 아이디입니다.";
        }else {
            return "사용중인 아이디입니다.";
        }
    }
}
