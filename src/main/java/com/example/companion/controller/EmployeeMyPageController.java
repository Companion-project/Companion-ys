package com.example.companion.controller;

import com.example.companion.command.EmployeeCommand;
import com.example.companion.service.employeeMyPage.EmployeeInfoService;
import com.example.companion.service.employeeMyPage.EmployeeInfoUpdateService;
import com.example.companion.service.employeeMyPage.EmployeePassConfirmService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("employee")
public class EmployeeMyPageController {

    @Autowired
    EmployeeInfoService employeeInfoService;

    @Autowired
    EmployeeInfoUpdateService employeeInfoUpdateService;

    @Autowired
    EmployeePassConfirmService employeePassConfirmService;

    @RequestMapping("empMyPage")
    public String empMyPage(HttpSession session, Model model){
        employeeInfoService.execute(session, model);
        return "worker/myInfo";
    }

    @GetMapping("employeeUpdate")
    public String employeeUpdate(HttpSession session, Model model){
        employeeInfoService.execute(session, model);
        return "worker/myModify";
    }

    @PostMapping("employeeUpdate")
    public String employeeUpdate(@Validated EmployeeCommand employeeCommand,
                                 BindingResult result, HttpSession session){
        employeeInfoUpdateService.execute(employeeCommand, session, result);
        if(result.hasErrors()){
            return "worker/myModify";
        }else{
            return "redirect:empMyPage";
        }
    }

    @GetMapping("employeePwModify")
    public String employeePwModify(){

        return "worker/myPwCon";
    }

    @PostMapping("employeePwModify")
    public String employeePwModify(@RequestParam("empPw")String empPw,
                                   Model model, HttpSession session){
        return "worker/myNewPw";
    }

    @PostMapping("empPwUpdate")
    public @ResponseBody boolean empPwUpdate( //boolean 타입으로 보내기 위해서는 ResponseBody사용
                          @RequestParam("oldPw") String oldPw,
                          @RequestParam("newPw") String newPw,
                          HttpSession session){
        return employeePassConfirmService.execute(newPw, oldPw, session);
    }


}
