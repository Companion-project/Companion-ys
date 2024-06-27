package com.example.companion.service.goodsIncoming;

import com.example.companion.command.GoodsIncomingCommand;
import com.example.companion.domain.AuthInfoDTO;
import com.example.companion.domain.GoodsIncomingDTO;
import com.example.companion.mapper.EmployeeMapper;
import com.example.companion.mapper.GoodsIncomingMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class GoodsIncomingService {

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    GoodsIncomingMapper goodsIncomingMapper;
    public void execute(GoodsIncomingCommand goodsIncomingCommand, HttpSession session){
        //입고한 직원정보 -> 직원 로그인 정보
        AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
        //아이디를 이용해서 직원정보 가져옴(직원번호가 필요해서)
        String empNum = employeeMapper.getEmpNum(auth.getUserId());
        //입고 DTO생성
        GoodsIncomingDTO dto = new GoodsIncomingDTO();
        dto.setEmpNum(empNum);
        dto.setGoodsNum(goodsIncomingCommand.getGoodsNum());
        dto.setIncomingDate(goodsIncomingCommand.getIncomingDate());
        dto.setIncomingNum(goodsIncomingCommand.getGoodsIncomingNum());
        dto.setIncomingPrice(goodsIncomingCommand.getIncomingPrice());
        dto.setIncomingQty(goodsIncomingCommand.getIncomingQty());
        dto.setProductionDate(Timestamp.valueOf(goodsIncomingCommand.getProductionDate()));
        goodsIncomingMapper.goodsIncomingInsert(dto);

    }
}
