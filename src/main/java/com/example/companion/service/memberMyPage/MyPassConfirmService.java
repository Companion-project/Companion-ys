package com.example.companion.service.memberMyPage;

import com.example.companion.domain.AuthInfoDTO;
import com.example.companion.mapper.MemberMyMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MyPassConfirmService {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    MemberMyMapper memberMyMapper;

    public boolean execute(String newPw, String oldPw, HttpSession session){
        AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
        if(passwordEncoder.matches(oldPw, auth.getUserPw())){
            String userPw = passwordEncoder.encode(newPw);
            memberMyMapper.memberPwUpdate(userPw, auth.getUserId());
            auth.setUserPw(userPw);
            return true;
        }else return false;
    }
}
