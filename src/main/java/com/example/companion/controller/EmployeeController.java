package com.example.companion.controller;

import com.example.companion.command.EmployeeCommand;
import com.example.companion.service.employees.EmployeeAutoNumService;
import com.example.companion.service.employees.EmployeeDeleteService;
import com.example.companion.service.employees.EmployeeInsertService;
import com.example.companion.service.employees.EmployeeListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("employee")
public class EmployeeController {
    @Autowired
    EmployeeAutoNumService employeeAutoNumService;

    @Autowired
    EmployeeInsertService employeeInsertService;

    @Autowired
    EmployeeListService employeeListService;

    @Autowired
    EmployeeDeleteService employeeDeleteService;

    @RequestMapping(value = "employeeList", method = RequestMethod.GET)
    //페이징, 검색기능 추가
    public String empList(
            @RequestParam(value = "page", required = false, defaultValue = "1")int page,
            @RequestParam(value = "searchWord", required = false)String searchWord,
            Model model){
        //직원 목록 기능
        employeeListService.execute(searchWord, page, model);
        return "employee/employeeList";
    }

    @GetMapping("empRegist")
    public String form(Model model){
        employeeAutoNumService.execute(model);
        return "employee/employeeForm";
    }

    @RequestMapping(value = "empRegist", method = RequestMethod.POST)
    //html에 있는 값을 command로 받아옴
    //html에서 넘어온 값에 대해 유효성 검사 진행
    public String form(@Validated EmployeeCommand employeeCommand, BindingResult result, Model model){
        if(result.hasErrors()){
            return "employee/employeeForm";
        }
        if(!employeeCommand.isEmpPwEqualsEmpPwCon()){
            System.out.println("비밀번호 확인이 다릅니다.");
            return "employee/employeeForm";

        }
        employeeInsertService.execute(employeeCommand);
        return "redirect:employeeList";
    }

    @PostMapping("empsDelete")
    public String empsDelete(
            @RequestParam(value = "empsDel")String empsDel[]){
        employeeDeleteService.execute(empsDel);
        return "redirect:employeeList";
    }



}
