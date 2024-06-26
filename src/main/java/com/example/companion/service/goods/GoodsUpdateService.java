package com.example.companion.service.goods;

import com.example.companion.command.GoodsCommand;
import com.example.companion.domain.AuthInfoDTO;
import com.example.companion.domain.GoodsDTO;
import com.example.companion.mapper.EmployeeMapper;
import com.example.companion.mapper.GoodsMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Service
public class GoodsUpdateService {

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    GoodsMapper goodsMapper;

    public void execute(GoodsCommand goodsCommand, HttpSession session,
                        BindingResult result, Model model){
        GoodsDTO dto = new GoodsDTO();
        dto.setGoodsContent(goodsCommand.getGoodsContent());
        dto.setGoodsName(goodsCommand.getGoodsName());
        dto.setGoodsNum(goodsCommand.getGoodsNum());
        dto.setGoodsPrice(goodsCommand.getGoodsPrice());
        dto.setDeliveryCost(goodsCommand.getDeliveryCost());
        //로그인 정보를 이용해 수정한 직원의 정보를 가져옴
        AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
        String empId = auth.getUserId();
        String empNum = employeeMapper.getEmpNum(empId);
        dto.setUpdateEmpNum(empNum);
        goodsMapper.goodsUpdate(dto);

    }
}
