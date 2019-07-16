package com.zcf.zhenxuan.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zcf.zhenxuan.bean.Address;
import com.zcf.zhenxuan.bean.Order;
import com.zcf.zhenxuan.bean.Shop;
import com.zcf.zhenxuan.controller.pay.AliPays.ALiPayConfig;
import com.zcf.zhenxuan.mapper.AddressMapper;
import com.zcf.zhenxuan.mapper.OrderMapper;
import com.zcf.zhenxuan.mapper.ShopMapper;
import com.zcf.zhenxuan.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;


@Controller
@RequestMapping("/alipay")
public class ALiPayController {

    @Autowired
    OrderMapper orderMapper;
    @Autowired
    AddressMapper addressMapper;
    @Autowired
    ShopMapper shopMapper;


    @RequestMapping("payAlis")
    @ResponseBody
    public String doGetCode(HttpServletRequest req, Order orders, Double mo)
            throws Exception {
        String order = null;// �?后的返回�?
        order = payIn(order, orders, mo);
        return order;
    }

    public String payIn(String order, Order orders, Double mo) {
        try {
            // DecimalFormat dex = new DecimalFormat("#0.00");
            // String payAccount = String.valueOf(dex.format(1));//
            // 测试使用（可删除�?

            // 实例化客户端
            AlipayClient alipayClient = new DefaultAlipayClient(
                    "https://openapi.alipay.com/gateway.do",
                    ALiPayConfig.appid, ALiPayConfig.private_key, "json",
                    ALiPayConfig.input_charset, ALiPayConfig.ali_public_key,
                    ALiPayConfig.pay_sign_type);
            // 实例化具体API对应的request�?,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
            AlipayTradeAppPayRequest request1 = new AlipayTradeAppPayRequest();
            // SDK已经封装掉了公共参数，这里只�?要传入业务参数�?�以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)�?
            AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
            model.setBody("欢迎使用51臻选");
            model.setSubject("欢迎购买51臻选商品价格为:" + orders.getTotalmoney() + "元");
            model.setOutTradeNo(orders.getOrderid());
            model.setTimeoutExpress("30m");
            model.setTotalAmount(mo + "");
            model.setSellerId(ALiPayConfig.partner);
            model.setProductCode("QUICK_MSECURITY_PAY");
            request1.setBizModel(model);
            request1.setNotifyUrl(ALiPayConfig.notify_url);
            try {
                // 这里和普通的接口调用不同，使用的是sdkExecute
                AlipayTradeAppPayResponse response = alipayClient
                        .sdkExecute(request1);
                System.out.println(response.getBody());
                order = response.getBody();
            } catch (AlipayApiException e) {
                e.getMessage();
                order = "error";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            order = "error";
        }
        return order;
    }

    // 购买商品：支付后异步请求地址
    @RequestMapping("notify_url")
    public void doOrderSecPayNotify(HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>进入回调方法");
        try {
            // 获取支付宝POST过来反馈信息
            Map<String, String> params = new HashMap<String, String>();
            @SuppressWarnings("rawtypes")
            Map requestParams = request.getParameterMap();
            for (@SuppressWarnings("rawtypes")
                 Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
                String name = (String) iter.next();
                String[] values = (String[]) requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i]
                            : valueStr + values[i] + ",";
                    valueStr = new String(valueStr.getBytes("utf-8"), "utf-8");
                    System.out.println(valueStr);
                }
                // 乱码解决，这段代码在出现乱码时使用�??
                params.put(name, valueStr.trim());
            }
            /**
             * System.out.println("--->获取支付宝POST过来反馈信息");
             * 切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看�??
             * boolean AlipaySignature.rsaCheckV1(Map<String, String> params,
             * String publicKey, String charset, String sign_type)
             */
            try {
                boolean flag = AlipaySignature.rsaCheckV1(params,
                        ALiPayConfig.ali_public_key,
                        ALiPayConfig.input_charset, "RSA2");
                // boolean flag = AlipaySignature.rsaCheckV1(params,
                // ALiPayConfig.ali_public_key,
                // "UTF-8",ALiPayConfig.pay_sign_type);
                // 商户订单�?
                String out_trade_no = new String(request.getParameter(
                        "out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
                // 支付宝交易号
                String trade_no = new String(request.getParameter("trade_no")
                        .getBytes("ISO-8859-1"), "UTF-8");
                // 交易状�??
                String trade_status = new String(request.getParameter(
                        "trade_status").getBytes("ISO-8859-1"), "UTF-8");
                // 支付金额
                String totalFee = new String(request.getParameter(
                        "buyer_pay_amount").getBytes("ISO-8859-1"), "UTF-8");
                // 买家账号
                String buyerEmail = new String(request.getParameter(
                        "buyer_logon_id").getBytes("ISO-8859-1"), "UTF-8");
                System.out.println(">>>>>>>>>>>>>>>商户订单�?:" + out_trade_no);
                System.out.println(">>>>>>>>>>>>>>>>>支付宝交易号" + trade_no);
                System.out
                        .println(">>>>>>>>>>>>>>>>>>>>交易状�??" + trade_status);
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>支付金额" + totalFee);
                System.out.println(">>>>>>>>>>>>>>>>>>>>买家账号" + buyerEmail);

                Order order = orderMapper.selectById(out_trade_no);
                if ("0".equals(order.getStatus())) {
                    order.setStatus("1");
                    order.setPaytime(DateUtils.formatTimeNow());
                    Address address = addressMapper.selectById(order.getAddressid());
                    Shop shop = shopMapper.selectById(order.getShopid());
                    sendsms1.sendSms(out_trade_no, address.getUsername(), shop.getTel(), order.getTotalmoney(), address.getTelephone());
                }

                orderMapper.updateById(order);


            } catch (AlipayApiException e1) {
                System.out.println("fail2");
            }
        } catch (UnsupportedEncodingException e) {
            System.out.println("fail3");
        }
    }

    // 查询支付宝的支付状�??
    // @RequestMapping("carts/query")
    // public ModelAndView QueryStatus( ){
    // System.err.println("进入该方�?");
    // return new ModelAndView(new RedirectView("http://www.baidu.com"));
    // }

}