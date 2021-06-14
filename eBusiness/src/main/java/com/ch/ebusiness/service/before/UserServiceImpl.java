package com.ch.ebusiness.service.before;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.ch.ebusiness.entity.Eva;
import com.ch.ebusiness.util.MyUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import com.ch.ebusiness.entity.BUser;
import com.ch.ebusiness.repository.before.UserRepository;
import com.ch.ebusiness.util.MD5Util;
@Service
public class UserServiceImpl implements UserService {
	@Autowired 
	private UserRepository userRepository;
	@Override
	public String isUse(BUser bUser) {
		if(userRepository.isUse(bUser).size() > 0) {
			return "no";
		}
		return "ok";
	}
	@Override
	public String register(BUser bUser) {
		//对密码MD5加密
		//bUser.setBpwd(MD5Util.MD5(bUser.getBpwd()));
		if(userRepository.register(bUser) > 0) {
			return "user/login";
		}
		return "user/register";
	}
	@Override
	public String login(BUser bUser, HttpSession session, Model model) {
		//对密码MD5加密
		//bUser.setBpwd(MD5Util.MD5(bUser.getBpwd()));
		String rand = (String)session.getAttribute("rand");
		if(!rand.equalsIgnoreCase(bUser.getCode())) {
			model.addAttribute("errorMessage", "验证码错误！");
			return "user/login";
		}
		List<BUser> list = userRepository.login(bUser);
		if(list.size() > 0) {
			session.setAttribute("bUser", list.get(0));
			if(list.get(0).getGender()==1) {
				session.setAttribute("genderM", true);
				session.setAttribute("genderW", false);
			} else {
				session.setAttribute("genderM", false);
				session.setAttribute("genderW", true);
			}
			return "redirect:/";//到首页
		}
		model.addAttribute("errorMessage", "用户名或密码错误！");
		return "user/login";
	}

	@Override
	public String updateRecharge(HttpSession session) {
		Integer uid = MyUtil.getUser(session).getId();
		userRepository.updateRecharge(uid);
		List<BUser> list = userRepository.updateBUser(uid);
		if(list.size() > 0) {
			session.setAttribute("bUser", list.get(0));
			return "redirect:/";//到首页
		}
		return "forward:/user/toLogin";
	}

	@Override
	public String evaluation(HttpSession session,Model model, Integer srating, String storeEva, Integer grating, String goodsEva, Integer id) {
		if(srating != null){
			Integer buser_id = MyUtil.getUser(session).getId();
			Integer store_id = userRepository.getSid((Integer) session.getAttribute("goodstable_id"));
			userRepository.insertEva(grating, goodsEva, srating, storeEva, (Integer) session.getAttribute("goodstable_id"), store_id, buser_id);
			return "forward:/cart/myOder";
		} else{
			List<Map<String, Object>> storeInfo =userRepository.store(id);
			model.addAttribute("storeInfo", storeInfo.get(0));
			session.setAttribute("goodstable_id",id);
			return "user/evaluation";
		}

	}

}
