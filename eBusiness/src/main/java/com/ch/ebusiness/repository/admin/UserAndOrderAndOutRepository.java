package com.ch.ebusiness.repository.admin;

import java.util.List;
import java.util.Map;

import com.ch.ebusiness.entity.Store;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ch.ebusiness.entity.BUser;

@Mapper
public interface UserAndOrderAndOutRepository {
	List<BUser> selectUserByPage(@Param("startIndex") int startIndex, @Param("perPageSize") int perPageSize);
	int selectAllUser();
	int selectAllStore();
	List<Store> selectStoreByPage(@Param("startIndex") int startIndex, @Param("perPageSize") int perPageSize);
	List<Map<String, Object>> selectCartUser(int id);
	List<Map<String, Object>> selectOrderUser(int id);
	int deleteUser(int id);
	int deleteStore(int id);
	int selectAllOrder();

	List<Map<String, Object>> selectOrderByPage(@Param("startIndex") int startIndex, @Param("perPageSize") int perPageSize);
	public List<Integer> getGid(@Param("oid") Integer oid);
	public Integer getNum(@Param("gid") Integer gid, @Param("oid") Integer oid);
	public double getPrice(@Param("gid") Integer gid);
	public Integer getSman(@Param("gid") Integer gid);
	public int addWallet(@Param("man") Integer man, @Param("sum") double sum);
	public int endOrder(@Param("oid") Integer oid);
}
