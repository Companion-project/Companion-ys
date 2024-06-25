package com.example.companion.service.employeeMyPage;

import com.example.companion.command.EmployeeCommand;
import com.example.companion.domain.AuthInfoDTO;
import com.example.companion.domain.EmployeeDTO;
import com.example.companion.mapper.EmployeeMyMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class EmployeeInfoService {

    @Autowired
    EmployeeMyMapper employeeMyMapper;

    public void execute(HttpSession session, Model model){

        AuthInfoDTO authInfoDTO = (AuthInfoDTO)session.getAttribute("auth");
        String empId = authInfoDTO.getUserId(); //로그인 시 저장한 아이디 불러옴

        EmployeeDTO dto = employeeMyMapper.employeeInfo(empId);
        //dto에 저장한 값을 command에 다시 저장
        EmployeeCommand employeeCommand = new EmployeeCommand();
        employeeCommand.setEmpAddr(dto.getEmpAddr());
        employeeCommand.setEmpAddrDetail(dto.getEmpAddrDetail());
        employeeCommand.setEmpEmail(dto.getEmpEmail());
        employeeCommand.setEmpId(dto.getEmpId());
        employeeCommand.setEmpSsn(dto.getEmpSsn());
        employeeCommand.setEmpName(dto.getEmpName());
        employeeCommand.setEmpNum(dto.getEmpNum());
        employeeCommand.setEmpPhone(dto.getEmpPhone());
        employeeCommand.setEmpPost(dto.getEmpPost());
        employeeCommand.setEmpRegiDate(dto.getEmpRegiDate());
        //hrml에 출력 가능하도록 model에 저장
        model.addAttribute("employeeCommand", employeeCommand);

    }
}
