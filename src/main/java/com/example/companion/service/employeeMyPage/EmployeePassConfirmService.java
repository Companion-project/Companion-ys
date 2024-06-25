package com.example.companion.service.employeeMyPage;

import com.example.companion.domain.AuthInfoDTO;
import com.example.companion.mapper.EmployeeMyMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmployeePassConfirmService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    EmployeeMyMapper employeeMyMapper;

    public boolean execute(String newPw, String oldPw, HttpSession session){
        AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");

        if(passwordEncoder.matches(oldPw, auth.getUserPw())){

            String userPw = passwordEncoder.encode(newPw);
            employeeMyMapper.employeePwUpdate(userPw, auth.getUserId()); //변경된 비밀번호 DB에 저장
            auth.setUserPw(userPw); //변경된 비밀번호 세션에 저장
            return true;

        }else{
            return false;
        }
    }
}
