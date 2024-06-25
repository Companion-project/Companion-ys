package com.example.companion.service.goods;

import com.example.companion.command.GoodsCommand;
import com.example.companion.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class GoodsAutoNumService {

    @Autowired
    GoodsMapper goodsMapper;

    public void  execute(Model model){
        //prod글자수에 따라 숫자의 변화를 줄 수도 있음.(현재 gd로 변경)
        //번호 예시 : gd100001 처럼 생성되어짐.
        String goodsNum = goodsMapper.incomingAndGoodsAutoNum("goods", "goods_num", "gd", 3);
        GoodsCommand goodsCommand = new GoodsCommand();
        goodsCommand.setGoodsNum(goodsNum);
        model.addAttribute("goodsCommand", goodsCommand);
    }
}
