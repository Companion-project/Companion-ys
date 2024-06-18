package com.example.companion.controller;

import com.example.companion.service.EmailCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@ResponseBody와 같은 방법이나 다른 형식인 @RestController사용
@RestController
public class CheckRestController {
    @Autowired
    EmailCheckService emailCheckService;

    @RequestMapping(value="checkRest/userEmailCheck", method = RequestMethod.POST)
    public String userEmailCheck(@RequestParam(value = "userEmail") String userEmail){
        String resultEmail = emailCheckService.execute(userEmail);
        if (userEmail == null) {
            return "사용가능한 이메일입니다.";
        } else{
            return "사용중인 이메일입니다.";
        }
    }
}
