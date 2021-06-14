package com.ch.ebusiness.service.before;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.ch.ebusiness.entity.BUser;

public interface UserService {
	public String isUse(BUser bUser);
	public String register(BUser bUser);
	public String login(BUser bUser, HttpSession session, Model model);
    public String updateRecharge(HttpSession session);
	public String evaluation(HttpSession session, Model model, Integer srating, String storeEva, Integer grating, String goodsEva, Integer id);
}
