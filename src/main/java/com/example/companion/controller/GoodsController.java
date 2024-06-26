package com.example.companion.controller;

import com.example.companion.command.GoodsCommand;
import com.example.companion.service.goods.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    ProductsDeleteService productsDeleteService;

    @PostMapping("productsDelete")
    public String productsDelete(
            @RequestParam(value = "memDels")String memDels[]){
        productsDeleteService.execute(memDels);
        return "redirect:goodsList";
    }

    @Autowired
    GoodsDetailService goodsDetailService;

    @GetMapping("goodsDetail")
    public String goodsDetail(@RequestParam("goodsNum") String goodsNum,
                              Model model){
        goodsDetailService.execute(goodsNum, model);
        return "goods/goodsInfo";
    }

    @GetMapping("goodsUpdate")
    public String goodsUpdate(@RequestParam("goodsNum") String goodsNum,
                              Model model){
        goodsDetailService.execute(goodsNum, model);
        return "goods/goodsModify";
    }

    @Autowired
    GoodsUpdateService goodsUpdateService;
    @PostMapping("goodsUpdate")
    public String goodsUpdate(@Validated GoodsCommand goodsCommand, BindingResult result,
                              HttpSession session, Model model){
        goodsUpdateService.execute(goodsCommand, session, result, model);
        if(result.hasErrors()){
            return "goods/goodsModify";
        }
        return "redirect:goodsDetail?goodsNum="+goodsCommand.getGoodsNum();
    }

    @Autowired
    GoodsDeleteService goodsDeleteService;

    @RequestMapping("goodsDel/{goodsNum}")
    public String goodsDel(@PathVariable("goodsNum") String goodsNum){
        goodsDeleteService.execute(goodsNum);
        return "redirect:../goodsList"; //PathVariable사용 시 주소 앞에 .. 꼭 붙여줘야 함 주의!
    }

}
