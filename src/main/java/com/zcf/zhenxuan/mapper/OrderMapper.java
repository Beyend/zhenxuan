package com.zcf.zhenxuan.mapper;

import com.zcf.zhenxuan.bean.Order;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zcf.zhenxuan.vo.in.UserParam;
import com.zcf.zhenxuan.vo.out.OrderTotalVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author kmr123
 * @since 2018-12-18
 */
public interface OrderMapper extends BaseMapper<Order> {

    List getordersListbyall(UserParam param);

    OrderTotalVo getShopTotal(@Param(value = "shopid") String shopid,@Param(value = "begDate") String begDate,@Param(value = "endDate") String endDate);

    OrderTotalVo getShopRefund(@Param(value = "shopid") String shopid);

    OrderTotalVo getAdminTotal(@Param(value = "cityid") String cityid);
}
