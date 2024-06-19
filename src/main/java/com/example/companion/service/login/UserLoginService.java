package com.example.companion.service.login;

import com.example.companion.command.LoginCommand;
import com.example.companion.domain.AuthInfoDTO;
import com.example.companion.mapper.UserMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class UserLoginService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    PasswordEncoder passwordEncoder;

    public void execute(LoginCommand loginCommand, HttpSession session, BindingResult result){
        String userId = loginCommand.getUserId();
        String userPw = loginCommand.getUserPw();

        AuthInfoDTO dto = userMapper.loginSelect(userId);
        if(userId != "" && userId != null){
            if(dto!=null){
                if(dto.getUserEmailCheck()==null){
                    System.out.println("아이디는 있지만 이메일 인증이 되지 않았을 때");
                    result.rejectValue("userId", "loginCommand.userId", "이메일 인증이 되지 않았습니다.");
                }else{
                    //아이디가 존재하고 비밀번호가 일치하는 경우
                    //userPw는 로그인창 입력값, dto.getUserPw는 db에서 가져온 암호화된 문자열
                    if(passwordEncoder.matches(userPw, dto.getUserPw())){
                        System.out.println("password matched");
                        //아이디, 비밀번호가 일치하면 session에 로그인 정보 저장
                        session.setAttribute("auth", dto);
                    }else{
                        System.out.println("password not matched");
                        result.rejectValue("userPw", "loginCommand.userPw"
                                ,"비밀번호가 틀렸습니다.");                    }
                }
            }else{
                System.out.println("아이디가 존재 하지 않았을 때");
                result.rejectValue("userId", "loginCommand.userId", "아이디가 존재하지 않습니다.");
            }
        }
    }
}
