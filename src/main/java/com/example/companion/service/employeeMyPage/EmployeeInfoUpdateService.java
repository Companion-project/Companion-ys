package com.example.companion.service.employeeMyPage;

import com.example.companion.command.EmployeeCommand;
import com.example.companion.domain.AuthInfoDTO;
import com.example.companion.domain.EmployeeDTO;
import com.example.companion.mapper.EmployeeMyMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class EmployeeInfoUpdateService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    EmployeeMyMapper employeeMyMapper;

    public int execute(EmployeeCommand employeeCommand, HttpSession session, BindingResult result){
        AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
        if(! passwordEncoder.matches(employeeCommand.getEmpPw(), auth.getUserPw())){
            result.rejectValue("empPw", "employeeCommand.empPw", "비밀번호가 일치하지 않습니다.");
            return 0;
        }else{
            EmployeeDTO dto = new EmployeeDTO();
            dto.setEmpAddr(employeeCommand.getEmpAddr());
            dto.setEmpAddrDetail(employeeCommand.getEmpAddrDetail());
            dto.setEmpSsn(employeeCommand.getEmpSsn());
            dto.setEmpEmail(employeeCommand.getEmpEmail());
            dto.setEmpName(employeeCommand.getEmpName());
            dto.setEmpNum(employeeCommand.getEmpNum());
            dto.setEmpPhone(employeeCommand.getEmpPhone());
            dto.setEmpPost(employeeCommand.getEmpPost());
            dto.setEmpRegiDate(employeeCommand.getEmpRegiDate());
            employeeMyMapper.employeeInfoUpdate(dto);
            return 1;
        }
    }
}
