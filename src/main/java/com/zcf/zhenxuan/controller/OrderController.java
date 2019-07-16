package com.zcf.zhenxuan.controller;


import com.zcf.zhenxuan.service.OrderService;
import com.zcf.zhenxuan.util.json.Body;
import com.zcf.zhenxuan.vo.in.UserParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author kmr123
 * @since 2018-12-18
 */
@Controller
@RequestMapping("/order")
@ResponseBody
@CrossOrigin
public class OrderController {
    @Autowired
    OrderService orderService;

    /**
     * 创建订单
     *
     * @param userid
     * @param goodsid
     * @param num
     * @return
     */
    @PostMapping("/addorder")
    public Body addorder(String userid, String goodsid, String num) {
        return orderService.addorder(userid, goodsid, num);
    }

    /**
     * 订单付款
     *
     * @param orderid
     * @param paytype
     * @return
     */
    @PostMapping("/payorder")
    public Body payorder(String orderid, String paytype, String addressid, HttpServletRequest req, HttpServletResponse response) throws Exception {
        return orderService.payorder(orderid, paytype, addressid, req, response);
    }

    /**
     * 查看定单
     *
     * @param userid
     * @param status
     * @return
     */
    @PostMapping("/getorderlist")
    public Body getorderlist(String userid, String status) {
        return orderService.getorderlist(userid, status);
    }

    /**
     * 订单发货
     *
     * @param orderid
     * @return
     */
    @PostMapping("/changeorderstatus")
    public Body changeorderstatus(String orderid) {
        return orderService.changeorderstatus(orderid);
    }

    /**
     * 确认收货
     *
     * @param orderid
     * @return
     */
    @PostMapping("/sureorder")
    public Body sureorder(String orderid) {
        return orderService.sureorder(orderid);
    }

    /**
     * 订单详情
     *
     * @return
     */
    @PostMapping("/getOrderinfo")
    public Body getOrderinfo(String orderid) {
        return orderService.getOrderinfo(orderid);
    }

    /**
     * 申请退款
     *
     * @param orderid
     * @return
     */
    @PostMapping("/returnOrder")
    public Body returnOrder(String orderid) throws Exception {
        return orderService.returnOrder(orderid);
    }

    /**
     * 审核退款结果
     *
     * @param orderid
     * @param type
     * @return
     */
    @PostMapping("/checkorder")
    public Body checkorder(String orderid, String type) throws Exception {
        return orderService.checkorder(orderid, type);
    }

    /**
     * 总后台查看订单根据城市
     *
     * @param param
     * @return
     */
    @PostMapping("/getordersListbyall")
    public Body getordersListbyall(UserParam param) {
        return orderService.getordersListbyall(param);
    }

    /**
     * 取消订单
     *
     * @param orderid
     * @return
     */
    @PostMapping("/cancleOrder")
    public Body cancleOrder(String orderid) {
        return orderService.cancleOrder(orderid);
    }



}

