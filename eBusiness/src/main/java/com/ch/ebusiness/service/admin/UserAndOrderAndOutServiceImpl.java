package com.ch.ebusiness.service.admin;

import java.util.List;
import java.util.Map;

import com.ch.ebusiness.entity.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.ch.ebusiness.entity.BUser;
import com.ch.ebusiness.repository.admin.UserAndOrderAndOutRepository;
@Service
public class UserAndOrderAndOutServiceImpl implements UserAndOrderAndOutService{
	@Autowired
	private UserAndOrderAndOutRepository userAndOrderAndOutRepository;

	@Override
	public String selectUser(Model model, int currentPage) {
		//共多少个用户
	  	int totalCount = userAndOrderAndOutRepository.selectAllUser();
	  	//计算共多少页
	  	int pageSize = 5;
	  	int totalPage = (int)Math.ceil(totalCount*1.0/pageSize);
	  	List<BUser> typeByPage = userAndOrderAndOutRepository.selectUserByPage((currentPage-1)*pageSize, pageSize);
	    model.addAttribute("allUsers", typeByPage);
	    model.addAttribute("totalPage", totalPage);
	    model.addAttribute("currentPage", currentPage);
		return "admin/allUser";
	}

	@Override
	public String deleteUser(Model model, int id) {
		if(userAndOrderAndOutRepository.selectCartUser(id).size() > 0 
				||userAndOrderAndOutRepository.selectOrderUser(id).size() > 0) {
			return "no";
		}else {
			userAndOrderAndOutRepository.deleteUser(id);
			return "/selectUser?currentPage=1";
		}
	}

	@Override
	public String selectOrder(Model model, int currentPage) {
		//共多少个订单
	  	int totalCount = userAndOrderAndOutRepository.selectAllOrder();
	  	//计算共多少页
	  	int pageSize = 5;
	  	int totalPage = (int)Math.ceil(totalCount*1.0/pageSize);
	  	List<Map<String, Object>> orderByPage = userAndOrderAndOutRepository.selectOrderByPage((currentPage-1)*pageSize, pageSize);
	    model.addAttribute("allOrders", orderByPage);
	    model.addAttribute("totalPage", totalPage);
	    model.addAttribute("currentPage", currentPage);
		return "admin/allOrder";
	}

	@Override
	public String allWallet(Model model, Integer oid) {
		List<Integer> gid = userAndOrderAndOutRepository.getGid(oid);
		for (int i=0; i<gid.size();i++){
			Integer num = userAndOrderAndOutRepository.getNum(gid.get(i),oid);
			double price = userAndOrderAndOutRepository.getPrice(gid.get(i));
			double sum = num*price;
			Integer man = userAndOrderAndOutRepository.getSman(gid.get(i));
			userAndOrderAndOutRepository.addWallet(man,sum);
		}
		userAndOrderAndOutRepository.endOrder(oid);
		return "forward:selectOrder?currentPage=1";
	}

	@Override
	public String selectStore(Model model, int currentPage) {
		//共多少个用户
		int totalCount = userAndOrderAndOutRepository.selectAllStore();
		//计算共多少页
		int pageSize = 5;
		int totalPage = (int)Math.ceil(totalCount*1.0/pageSize);
		List<Store> typeByPage = userAndOrderAndOutRepository.selectStoreByPage((currentPage-1)*pageSize, pageSize);
		model.addAttribute("allStore", typeByPage);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("currentPage", currentPage);
		return "admin/allStore";
	}

	@Override
	public String deleteStore(Model model, int id) {
		userAndOrderAndOutRepository.deleteStore(id);
		return "/selectStore?currentPage=1";
	}
}
