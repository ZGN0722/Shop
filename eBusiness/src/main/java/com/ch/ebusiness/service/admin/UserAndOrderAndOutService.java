package com.ch.ebusiness.service.admin;

import org.springframework.ui.Model;

public interface UserAndOrderAndOutService {
	public String selectUser(Model model, int currentPage);
	public String deleteUser(Model model, int id);
	public String selectOrder(Model model, int currentPage);
	public String allWallet(Model model, Integer oid);
	public String selectStore(Model model, int currentPage);
	public String deleteStore(Model model, int id);
}
