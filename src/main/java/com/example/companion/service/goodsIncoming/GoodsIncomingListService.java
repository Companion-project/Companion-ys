package com.example.companion.service.goodsIncoming;

import com.example.companion.domain.GoodsIncomingDTO;
import com.example.companion.mapper.GoodsIncomingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class GoodsIncomingListService {

    @Autowired
    GoodsIncomingMapper goodsincomingMapper;

    public void execute(Model model){
        List<GoodsIncomingDTO> list = goodsincomingMapper.goodsIncomingAllSelect();
        model.addAttribute("list", list);

    }
}
