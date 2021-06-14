package com.ch.ebusiness.service.store;

import com.ch.ebusiness.entity.Store;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;
import java.io.IOException;

public interface StoreService {
    //认证商家
    public String register(Store store, Model model, HttpSession session)throws IllegalStateException, IOException;
    //商店信息
    public String storeInfo(Store store, Model model, HttpSession session);
    //商店商品
    public String selectMyGoods(Model model, int currentPage, String act, HttpSession session);
    //订单信息
    public String selectOrder(Model model, int currentPage, HttpSession session);
}
