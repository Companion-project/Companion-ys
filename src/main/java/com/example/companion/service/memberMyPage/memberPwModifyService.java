package com.example.companion.service.memberMyPage;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class memberPwModifyService {

    @Autowired
    PasswordEncoder passwordEncoder; //암호화된 비밀번호 비교

    public String execute(HttpSession session, Model model, String memberPw){
        //
        return null;


    }
}
