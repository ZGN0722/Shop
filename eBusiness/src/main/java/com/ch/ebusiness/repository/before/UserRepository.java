package com.ch.ebusiness.repository.before;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ch.ebusiness.entity.BUser;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserRepository {
	public List<BUser> isUse(BUser bUser);
	public int register(BUser bUser);
	public List<BUser> login(BUser bUser);
	public int updateRecharge(@Param("uid") Integer uid);
	public List<BUser> updateBUser(@Param("uid") Integer uid);
	public double updateScore(@Param("uid") Integer uid, @Param("amount") double amount);
	public int insertEva(@Param("grating") Integer grating, @Param("goodsEva") String goodsEva, @Param("srating") Integer srating, @Param("storeEva") String storeEva, @Param("id")Integer id, @Param("sid")Integer sid ,@Param("buser_id")Integer buser_id);
	List<Map<String, Object>> store(@Param("id") Integer id);
	public int getSid(@Param("id") Integer id);
}
