package com.example.companion.mapper;

import com.example.companion.domain.AuthInfoDTO;
import com.example.companion.domain.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface  UserMapper {
    public int userInsert(MemberDTO dto);
    public int userCheckUpdate(String email);
    public AuthInfoDTO loginSelect(String userId);
}
