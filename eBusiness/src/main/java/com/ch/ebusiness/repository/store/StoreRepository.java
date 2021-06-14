package com.ch.ebusiness.repository.store;

import com.ch.ebusiness.entity.Eva;
import com.ch.ebusiness.entity.Goods;
import com.ch.ebusiness.entity.Store;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface StoreRepository {

    int register(@Param("sname") String sname, @Param("spicture") String spicture, @Param("cpicture") String cpicture, @Param("buser_id") Integer buser_id);
    List<Store> selectInfo(@Param("buser_id") Integer buser_id);
    int selectMyGoods(@Param("store_id") Integer store_id);
    List<Goods> selectMyGoodsByPage(@Param("startIndex") int startIndex, @Param("perPageSize") int perPageSize, @Param("store_id") Integer store_id);
    int selectAllOrder(@Param("store_id") Integer store_id);
    List<Map<String, Object>> selectOrderByPage(@Param("startIndex") int startIndex, @Param("perPageSize") int perPageSize, @Param("store_id") Integer store_id );
    public  List<Eva> selectEva(@Param("id") Integer id);
}
