package com.example.companion.controller;

import com.example.companion.command.EmployeeCommand;
import com.example.companion.service.EmailCheckService;
import com.example.companion.service.UserEmailCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@ResponseBody와 같은 방법이나 다른 형식인 @RestController사용
@RestController
public class CheckRestController {
    @Autowired
    EmailCheckService emailCheckService;

    @Autowired
    UserEmailCheckService userEmailCheckService;

    @RequestMapping(value = "checkRest/userEmailCheck", method = RequestMethod.POST)
    public String userEmailCheck(@RequestParam(value = "userEmail") String userEmail) {
        String resultEmail = emailCheckService.execute(userEmail);
        if (resultEmail == null) {
            return "사용가능한 이메일입니다.";
        } else {
            return "사용중인 이메일입니다.";
        }
    }

    @RequestMapping("userConfirm")
    public String userConfirm(@RequestParam(value = "chk") String chk) {
        int i = userEmailCheckService.execute(chk);
        if (i == 0)
            return "이미 인증되었습니다. ";
        else return "인증되었습니다.";
    }

    @RequestMapping(value = "empRegist", method = RequestMethod.POST)
    public String form(@Validated EmployeeCommand employeeCommand, BindingResult result, Model model){
        if(result.hasErrors()){
            return "employee/employeeForm";
        }
        return "redirect:employeeList";
    }
}