package com.example.companion.service.memberMyPage;

import com.example.companion.domain.AuthInfoDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class MemberPwModifyService {
    @Autowired
    PasswordEncoder passwordEncoder;

    public String execute(HttpSession session, Model model, String memberPw){
        AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
        if(passwordEncoder.matches(memberPw, auth.getUserPw())){
            return "memberShip/myNewPw";
        }else{
            model.addAttribute("errPw", "비밀번호가 틀렸습니다.");
            return "memberShip/myPwCon";
        }
    }
}
