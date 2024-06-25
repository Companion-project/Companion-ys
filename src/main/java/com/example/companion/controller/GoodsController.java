package com.example.companion.controller;

import com.example.companion.command.GoodsCommand;
import com.example.companion.service.goods.GoodsAutoNumService;
import com.example.companion.service.goods.GoodsListService;
import com.example.companion.service.goods.GoodsWriteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("goods")
public class GoodsController {

    @Autowired
    GoodsAutoNumService goodsAutoNumService;

    @Autowired
    GoodsListService goodsListService;

    @RequestMapping(value = "goodsList", method = RequestMethod.GET)
    public String goodsList(@RequestParam(value = "searchWord", required = false)String searchWord,
                            @RequestParam(value = "page", required = false, defaultValue = "1")int page,
                            Model model){
        goodsListService.execute(searchWord, model, page);
        return "goods/goodsList";
    }

    @GetMapping("goodsForm")
    public String goodsWrite(){
        return "goods/goodsWrite";
    }

    @GetMapping("goodsWrite")
    public String goodsWrite(Model model){
        goodsAutoNumService.execute(model);
        return "goods/goodsForm";
    }

    @Autowired
    GoodsWriteService goodsWriteService;

    @RequestMapping(value = "goodsWrite", method = RequestMethod.POST)
    public String goodsWrite(@Validated GoodsCommand goodsCommand, BindingResult result,
             HttpSession session){
        if(result.hasErrors()){
            return "goods/goodsForm";
        }
        goodsWriteService.execute(goodsCommand, session);
        return "goods/goodsRedirect";
    }
}
