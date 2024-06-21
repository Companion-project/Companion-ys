package com.example.companion.controller;

import com.example.companion.command.EmployeeCommand;
import com.example.companion.service.employees.*;
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
    EmployeesDeleteService employeesDeleteService;
    @Autowired
    EmployeeDetailService employeeDetailService;
    @Autowired
    EmployeeUpdateService employeeUpdateService;
    @Autowired
    EmployeeDeleteService employeeDeleteService;

    @RequestMapping(value = "employeeList", method = RequestMethod.GET)
    public String empList(
        //paging, search functions added
        @RequestParam(value = "page", required = false, defaultValue = "1") int page,
        @RequestParam(value = "searchWord", required = false) String searchWord,
                Model model){
        employeeListService.execute(searchWord, page, model);
        return "employee/employeeList";
    }

    @GetMapping("empRegist")
    public String form(Model model) {
        employeeAutoNumService.execute(model);
        return "employee/employeeForm";
    }

    @RequestMapping(value = "empRegist", method = RequestMethod.POST)
    public String form(@Validated EmployeeCommand employeeCommand, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "employee/employeeForm";
        }
        if (!employeeCommand.isEmpPwEqualsEmpPwCon()) {
            System.out.println("비밀번호 확인이 다릅니다.");
            return "employee/employeeForm";
        }
        employeeInsertService.execute(employeeCommand);
        return "redirect:employeeList";
    }
    @PostMapping("empsDelete")
    public String employeesDelete(
            @RequestParam(value="empDels") String empsDel []) {
        employeesDeleteService.execute(empsDel);
        return "redirect:employeeList";
    }

    @RequestMapping(value = "employeeDetail", method = RequestMethod.GET)
    public String employeeDetail(@RequestParam(value = "empNum") String empNum, Model model) {
        employeeDetailService.execute(empNum, model);
        return "employee/empDetail";
    }

    @RequestMapping(value = "empModify", method = RequestMethod.GET)
    public String employeeUpdate(@RequestParam(value = "empNum") String empNum, Model model){
        employeeDetailService.execute(empNum, model);
        return "employee/employeeUpdate";
    }
    @RequestMapping(value = "empModify", method = RequestMethod.POST)
    public String employeeUpdate(@Validated EmployeeCommand employeeCommand, BindingResult result){
        if(result.hasErrors()){
            return "employee/employeeUpdate";
        }
        employeeUpdateService.execute(employeeCommand);
        return "redirect:employeeDetail?empNum=" + employeeCommand.getEmpNum();
    }

    @GetMapping("empDelete")
    public String employeeDelete(@RequestParam(value = "empNum") String empNum){
        employeeDeleteService.execute(empNum);
        return "redirect:employeeList";
    }
}

