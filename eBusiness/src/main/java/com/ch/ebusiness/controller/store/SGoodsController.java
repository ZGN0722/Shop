package com.ch.ebusiness.controller.store;

import com.ch.ebusiness.entity.Goods;
import com.ch.ebusiness.entity.Store;
import com.ch.ebusiness.service.store.SGoodsService;
import com.ch.ebusiness.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/sGoods")
public class SGoodsController {

    @Autowired
    private SGoodsService sGoodsService;

    @RequestMapping("/toAddGoods")
    public String toAddGoods(Store store, @ModelAttribute("goods") Goods goods, Model model, HttpSession session) {
        Integer uid = MyUtil.getUser(session).getId();
        goods.setIsAdvertisement(0);
        goods.setIsRecommend(1);
        return sGoodsService.toAddGoods(store, goods, model, uid);
    }
    @RequestMapping("/addGoods")
    public String addGoods(@ModelAttribute("goods") Goods goods, HttpServletRequest request, String act, HttpSession session) throws IllegalStateException, IOException {
        return sGoodsService.addGoods(goods, request, act, session);
    }
    @RequestMapping("/detail")
    public String detail(Model model, Integer id, String act) {
        return sGoodsService.detail(model, id, act);
    }
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(Integer id) {
        return sGoodsService.delete(id);
    }
}
