package com.example.companion.domain;

import lombok.Data;

@Data
public class AuthInfoDTO {
    String userId;
    String userPw;
    String userName;
    String grade;
    String userEmail;
    String userEmailCheck;
}
