package com.example.companion.service.goods;

import com.example.companion.domain.GoodsDTO;
import com.example.companion.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class GoodsListService {

    @Autowired
    GoodsMapper goodsMapper;

    public void execute(Model model){
        List<GoodsDTO> list = goodsMapper.allSelect();
        model.addAttribute("dtos", list);

    }
}
