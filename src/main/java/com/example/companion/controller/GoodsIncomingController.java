package com.example.companion.controller;

import com.example.companion.command.GoodsIncomingCommand;
import com.example.companion.domain.GoodsIncomingDTO;
import com.example.companion.service.goodsIncoming.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("goods")
public class GoodsIncomingController {

    @Autowired
    GoodsItemService goodsItemService;

    @Autowired
    GoodsIncomingAutoNumService goodsIncomingAutoNumService;

    @Autowired
    GoodsIncomingService goodsIncomingService;

    @Autowired
    GoodsIncomingListService goodsIncomingListService;

    @Autowired
    GoodsIncomingDetailService goodsIncomingDetailService;

    @PostMapping("goodsIncomingDetail")
    public @ResponseBody GoodsIncomingDTO detail(
            @RequestParam("incomingNum") String incomingNum,
            @RequestParam("goodsNum") String goodsNum){
        GoodsIncomingDTO dto = goodsIncomingDetailService.execute(incomingNum, goodsNum);
        return dto;

    }


    @PostMapping("goodsIncomingList")
    public ModelAndView goodsIncomingList(Model model){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("jsonView");
        goodsIncomingListService.execute(model);

        return mav;

    }

    @GetMapping("goodsIncomingList")
    public String goodsIncomingList(){
        return "goodsIncoming/goodsIncomingList";
    }

    @RequestMapping(value = "incomingRegist", method = RequestMethod.POST)
    public  String incomingRegist(GoodsIncomingCommand goodsIncomingCommand, HttpSession session){
        goodsIncomingService.execute(goodsIncomingCommand, session);
        return "redirect:goodsIncomingList";
    }

    @RequestMapping(value = "goodsIncoming", method = RequestMethod.GET)
    public String goodsIncoming(Model model){
        goodsIncomingAutoNumService.execute(model);
        return "goodsIncoming/goodsIncoming";
    }

    @GetMapping(value = "goodsItem")
    public String goodsItem(){
        return "goodsIncoming/goodsItem";
    }

    @PostMapping(value = "goodsItemList")
    @ResponseBody //map을 이용해 데이터 -> html전송하기 위해서 사용
    public Map<String, Object>goodsItem(
            @RequestParam(value = "searchWord", required = false) String searchWord,
            @RequestParam(value = "page", required = false, defaultValue = "1")int page
            ){
        Map<String, Object>map = goodsItemService.execute(searchWord, page);
        return map;
    }
}
