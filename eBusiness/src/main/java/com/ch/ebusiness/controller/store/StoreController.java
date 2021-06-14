package com.ch.ebusiness.controller.store;

import com.ch.ebusiness.entity.AUser;
import com.ch.ebusiness.entity.Store;
import com.ch.ebusiness.service.store.SGoodsService;
import com.ch.ebusiness.service.store.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/store")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @RequestMapping("/register")
    public String register(Store store, Model model, HttpSession session) throws IOException {
        return storeService.register(store, model, session);
    }

    @RequestMapping("/storeInfo")
    public String storeInfo(Store store, Model model, HttpSession session){
        return storeService.storeInfo(store, model, session);
    }

    @RequestMapping("/loginOut")
    public String loginOut() {
        return "redirect:/";
    }

    @RequestMapping("/selectMyGoods")
    public String selectMyGoods(Model model, int currentPage, String act, HttpSession session) {
        return storeService.selectMyGoods(model, currentPage, act, session);
    }

    @RequestMapping("/selectOrder")
    public String selectOrder(Model model, int currentPage, HttpSession session) {
        return storeService.selectOrder(model, currentPage, session);
    }

    @RequestMapping("/new")
    public String new2() {
        return "store/new";
    }


}
