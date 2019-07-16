package com.zcf.zhenxuan.service.impl;

import com.alipay.api.AlipayApiException;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.github.pagehelper.PageHelper;
import com.zcf.zhenxuan.bean.Goods;
import com.zcf.zhenxuan.bean.Order;
import com.zcf.zhenxuan.bean.Shop;
import com.zcf.zhenxuan.bean.User;
import com.zcf.zhenxuan.controller.ALiPayController;
import com.zcf.zhenxuan.controller.AliAndWXRefund;
import com.zcf.zhenxuan.controller.WXpayController;
import com.zcf.zhenxuan.controller.pay.WXpay.WxNotifyParam;
import com.zcf.zhenxuan.mapper.GoodsMapper;
import com.zcf.zhenxuan.mapper.OrderMapper;
import com.zcf.zhenxuan.mapper.ShopMapper;
import com.zcf.zhenxuan.service.OrderService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zcf.zhenxuan.util.DateUtils;
import com.zcf.zhenxuan.util.Hutool;
import com.zcf.zhenxuan.util.json.Body;
import com.zcf.zhenxuan.util.result.PageResult;
import com.zcf.zhenxuan.util.result.ResultVo;
import com.zcf.zhenxuan.vo.in.UserParam;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author kmr123
 * @since 2018-12-18
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    ShopMapper shopMapper;
    @Autowired
    OrderMapper orderMapper;

    @Override
    public Body addorder(String userid, String goodsid, String num) {
        Goods goods = goodsMapper.selectById(goodsid);
        if (goods.getNum() < Integer.parseInt(num)) {
            return Body.newInstance(777, "购买数量超出！");
        }
        Order order = new Order();
        order.setAddtime(DateUtils.formatTimeNow());
        order.setGoodsid(goodsid);
        order.setNum(Integer.parseInt(num));
        order.setOrderid(Hutool.getId());
        order.setStatus("0");
        order.setUserid(userid);

        order.setShopid(goods.getShopid());
        if ("1".equals(goods.getType())) {
            order.setTotalmoney(order.getNum() * Double.parseDouble(goods.getPrice()) + "");
        } else {
            order.setTotalmoney(order.getNum() * Double.parseDouble(goods.getSpike()) + "");
        }

        boolean b = this.insert(order);
        if (b) {
            return Body.newInstance(order);
        }
        return Body.BODY_500;
    }

    @Override
    public Body payorder(String orderid, String paytype, String addressid, HttpServletRequest req, HttpServletResponse response) throws Exception {
        Order order = this.selectById(orderid);
        order.setPaytype(paytype);
        order.setAddressid(addressid);
        this.updateById(order);
        if ("alipay".equals(paytype)) {
            ALiPayController aLiPayController = new ALiPayController();
            String data = aLiPayController.doGetCode(req, order, Double.parseDouble(order.getTotalmoney()));
            return Body.newInstance(data);
        }
        if ("weixin".equals(paytype)) {
            WXpayController wXpayController = new WXpayController();
            WxNotifyParam param = wXpayController.initWx(req, response, order);
            return Body.newInstance(param);
        }
        return Body.newInstance(null);
    }

    @Override
    public Body getorderlist(String userid, String status) {
        EntityWrapper<Order> orderEntityWrapper = new EntityWrapper<>();
        orderEntityWrapper.eq("userid", userid);
        if (!StringUtils.isEmpty(status)) {
            orderEntityWrapper.eq("status", status);
        }
        orderEntityWrapper.orderBy("addtime", false);
        List<Order> orders = this.selectList(orderEntityWrapper);
        if (orders.size() > 0) {
            for (int i = 0; i < orders.size(); i++) {
                Shop shop = shopMapper.selectById(orders.get(i).getShopid());
                orders.get(i).setShop(shop);
                Goods goods = goodsMapper.selectById(orders.get(i).getGoodsid());
                orders.get(i).setGoods(goods);
            }
        }
        return Body.newInstance(orders);
    }

    @Override
    public Body changeorderstatus(String orderid) {
        Order order = this.selectById(orderid);
        order.setStatus("2");
        boolean b = this.updateById(order);
        if (b) {
            return Body.BODY_200;
        }
        return Body.BODY_500;
    }

    @Override
    public Body sureorder(String orderid) {
        Order order = this.selectById(orderid);
        order.setStatus("3");
        boolean b = this.updateById(order);
        if (b) {
            return Body.BODY_200;
        }
        return Body.BODY_500;
    }

    @Override
    public Body getOrderinfo(String orderid) {
        Order order = this.selectById(orderid);
        if (order != null) {
            order.setGoods(goodsMapper.selectById(order.getGoodsid()));
            order.setShop(shopMapper.selectById(order.getShopid()));

        }
        return Body.newInstance(order);
    }

    @Override
    public Body returnOrder(String orderid) {
        Order order = this.selectById(orderid);
        order.setStatus("5");
        order.setReturntype("0");
        Integer integer = orderMapper.updateById(order);
        if (integer > 0) {
            return Body.BODY_200;
        }
        return Body.BODY_500;
    }

    @Override
    public Body checkorder(String orderid, String type) throws Exception {
        Order order = this.selectById(orderid);
        order.setReturntype(type);
        boolean b = this.updateById(order);
        if (b) {
            if ("1".equals(type)) {
                if ("alipay".equals(order.getPaytype())) {
                    AliAndWXRefund aliAndWXRefund = new AliAndWXRefund();
                    int i = aliAndWXRefund.refundMoney(Double.parseDouble(order.getTotalmoney()), orderid);
                    if (i == 1) {
                        return Body.BODY_200;
                    }
                    return Body.BODY_500;
                }
                if ("weixin".equals(order.getPaytype())) {
                    AliAndWXRefund aliAndWXRefund = new AliAndWXRefund();
                    int i = aliAndWXRefund.wxRefund(Double.parseDouble(order.getTotalmoney()), orderid);
                    System.out.println(i + "--------------------------");
                    if (i == 1) {
                        return Body.BODY_200;
                    }
                    return Body.BODY_500;
                }
                return Body.BODY_500;
            }
        }
        return Body.BODY_500;
    }

    @Override
    public Body getordersListbyall(UserParam param) {
        // 没有设置，默认首页10条数据显示
        if (param.getPageNum() != null && param.getPageSize() != null) {
            PageHelper.startPage(param.getPageNum(), param.getPageSize());
        }
        // 自动分页
        PageResult result = PageResult.result(orderMapper.getordersListbyall(param));
        // 返回的vo
        ResultVo resultVo = new ResultVo();
        // 返回总条数
        List<Order> list = orderMapper.getordersListbyall(param);
        resultVo.setTotal((long) list.size());
        // 返回结果集合
        resultVo.setList(result.getRows());
        return Body.newInstance(resultVo);
    }

    @Override
    public Body cancleOrder(String orderid) {
        Integer integer = orderMapper.deleteById(orderid);
        if (integer > 0) {
            return Body.BODY_200;
        }
        return Body.BODY_500;
    }


    @Scheduled(cron = "0/50 * * * * *")
    public void deleteOrder() throws ParseException {

        EntityWrapper<Order> orderEntityWrapper = new EntityWrapper<>();
        orderEntityWrapper.eq("status", "0");
        List<Order> orders = this.selectList(orderEntityWrapper);
        if (orders.size() > 0) {
            for (int i = 0; i < orders.size(); i++) {
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-M-d HH:mm:ss");
                java.util.Date start = sdf.parse(orders.get(i).getAddtime());
                java.util.Date end = new Date();

                long cha = end.getTime() - start.getTime();
                System.out.println(cha + "-------------");
                if (cha > (1000 * 60 * 60 * 2)) {
                    this.deleteById(orders.get(i).getOrderid());
                }
            }
        }


    }
}
