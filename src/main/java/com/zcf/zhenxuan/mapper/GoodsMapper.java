package com.zcf.zhenxuan.mapper;

import com.zcf.zhenxuan.bean.Goods;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author kmr123
 * @since 2018-12-18
 */
public interface GoodsMapper extends BaseMapper<Goods> {

    List<Goods> getgoodsListbycity(String cityid);
}
