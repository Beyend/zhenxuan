package com.zcf.zhenxuan.service;

import com.alipay.api.AlipayApiException;
import com.zcf.zhenxuan.bean.Order;
import com.baomidou.mybatisplus.service.IService;
import com.zcf.zhenxuan.util.json.Body;
import com.zcf.zhenxuan.vo.in.UserParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kmr123
 * @since 2018-12-18
 */
public interface OrderService extends IService<Order> {

    Body addorder(String userid, String goodsid, String num);

    Body payorder(String orderid, String paytype, String addressid, HttpServletRequest req, HttpServletResponse response) throws Exception;

    Body getorderlist(String userid, String status);

    Body changeorderstatus(String orderid);

    Body sureorder(String orderid);

    Body getOrderinfo(String orderid);

    Body returnOrder(String orderid) throws Exception;

    Body checkorder(String orderid, String type) throws Exception;

    Body getordersListbyall(UserParam param);

    Body cancleOrder(String orderid);
}
