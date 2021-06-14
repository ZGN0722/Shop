package com.ch.ebusiness.repository.store;

import com.ch.ebusiness.entity.BUser;
import com.ch.ebusiness.entity.Goods;
import com.ch.ebusiness.entity.GoodsType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SGoodsRepository {
    int addGoods(Goods goods);
    int updateGoods(Goods goods);
    Goods selectAGoods(Integer id);
    int selectAllGoods();
    List<GoodsType> selectAllGoodsType();
    List<Goods> selectAllGoodsByPage(@Param("startIndex") int startIndex, @Param("perPageSize") int perPageSize);
    int  deleteAGoods(Integer id);
    List<Map<String, Object>> selectFocusGoods(Integer id);
    List<Map<String, Object>> selectCartGoods(Integer id);
    List<Map<String, Object>> selectOrderGoods(Integer id);
    List<BUser> isStore(@Param("uid") Integer uid);
}
