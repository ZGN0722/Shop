package com.ch.ebusiness.service.store;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ch.ebusiness.entity.Store;
import org.springframework.ui.Model;

import com.ch.ebusiness.entity.Goods;
import org.springframework.web.bind.annotation.ModelAttribute;

public interface SGoodsService {
    public String selectAllGoodsByPage(Model model, int currentPage, String act);
    public String addGoods(Goods goods, HttpServletRequest  request, String act, HttpSession session) throws IllegalStateException, IOException ;
    public String toAddGoods(Store store, Goods goods, Model model, Integer uid);
    public String detail(Model model, Integer id, String act);
    public String delete(Integer id);
}
