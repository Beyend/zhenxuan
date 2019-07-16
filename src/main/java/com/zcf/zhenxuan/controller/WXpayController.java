package com.zcf.zhenxuan.controller;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zcf.zhenxuan.bean.Address;
import com.zcf.zhenxuan.bean.Order;
import com.zcf.zhenxuan.bean.Shop;
import com.zcf.zhenxuan.controller.pay.WXpay.*;
import com.zcf.zhenxuan.mapper.AddressMapper;
import com.zcf.zhenxuan.mapper.OrderMapper;
import com.zcf.zhenxuan.mapper.ShopMapper;
import com.zcf.zhenxuan.util.DateUtils;
import org.jdom2.JDOMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/weixin")
public class WXpayController {

    @Autowired
    OrderMapper orderMapper;
    @Autowired
    AddressMapper addressMapper;
    @Autowired
    ShopMapper shopMapper;
    private static final Logger logger = LoggerFactory
            .getLogger(WXpayController.class);


    /**
     * 初始化微信支�?
     * <p>
     * // * @param request // * @param response // * @param orderId // * @return
     */
    // @RequestMapping("/pay/wx")
    // @ResponseBody
    public WxNotifyParam initWx(HttpServletRequest request,
                                HttpServletResponse response, Order order) {
        // Map<String, Object> map = new HashMap<String, Object>();
        // 获取生成预支付订单的请求�?
        PrepayIdRequestHandler prepayReqHandler = new PrepayIdRequestHandler(
                request, response);
        String totalFee = String.valueOf((int) (Double.parseDouble(order.getTotalmoney()) * 100));// 微信支付�?
        // �?--
        // 上线后，将此代码放开
        prepayReqHandler.setParameter("appid", ConstantUtil.APP_ID);
        prepayReqHandler.setParameter("body", ConstantUtil.BODY);
        prepayReqHandler.setParameter("mch_id", ConstantUtil.MCH_ID);
        String nonce_str = WXUtil.getNonceStr();
        prepayReqHandler.setParameter("nonce_str", nonce_str);
        prepayReqHandler.setParameter("notify_url", ConstantUtil.NOTIFY_URL);
        // String out_trade_no = userOrder.getOrderNo();
        String out_trade_no = order.getOrderid();
        prepayReqHandler.setParameter("out_trade_no", out_trade_no);
        // request.getRemoteAddr()
        // "14.23.150.211"
        prepayReqHandler.setParameter("spbill_create_ip", "47.99.94.250");
        String timestamp = WXUtil.getTimeStamp();
        prepayReqHandler.setParameter("time_start", timestamp);
        // logger.info("金额" + totalFee);
        prepayReqHandler.setParameter("total_fee", totalFee);
        prepayReqHandler.setParameter("trade_type", "APP");
        /**
         * 注意签名（sign）的生成方式，具体见官方文档（传参都要参与生成签名，且参数名按照字典序排序，�
         * ?后接上APP_KEY,转化成大写）
         */
        prepayReqHandler.setParameter("sign", prepayReqHandler.createMD5Sign());
        prepayReqHandler.setGateUrl(ConstantUtil.GATEURL);
        String prepayid;
        WxNotifyParam param = new WxNotifyParam();
        try {
            prepayid = prepayReqHandler.sendPrepay();
            // 若获取prepayid成功，将相关信息返回客户�?
            if (prepayid != null && !prepayid.equals("")) {
                String signs = "appid=" + ConstantUtil.APP_ID + "&noncestr="
                        + nonce_str + "&package=Sign=WXPay&partnerid="
                        + ConstantUtil.PARTNER_ID + "&prepayid=" + prepayid
                        + "&timestamp=" + timestamp + "&key="
                        + ConstantUtil.APP_KEY;
                /**
                 * 签名方式与上面类�?
                 */
                param.setPrepayId(prepayid);
                param.setSign(Md5Util.MD5Encode(signs, "utf8").toUpperCase());
                param.setAppId(ConstantUtil.APP_ID);
                // 等于请求prepayId时的time_start
                param.setTimeStamp(timestamp);
                // 与请求prepayId时�?�一�?
                param.setNonceStr(nonce_str);
                // 固定常量
                param.setPackages("Sign=WXPay");
                param.setPartnerId(ConstantUtil.PARTNER_ID);
                logger.info("-----------》创建微信订单成�?: " + param);
            } else {
                return null;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            return null;
        }
        return param;
    }

    /**
     * 接收微信支付回调通知
     *
     * @param request
     * @param response
     * @throws Exception
     */

    @RequestMapping("/wx/notify_url")
    public void getTenPayNotif(HttpServletRequest request,
                               HttpServletResponse response) throws Exception {
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>进入微信回调方法");
        PrintWriter writer = response.getWriter();
        InputStream inStream = request.getInputStream();
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outSteam.write(buffer, 0, len);
        }
        outSteam.close();
        inStream.close();
        String result = new String(outSteam.toByteArray(), "utf-8");
        logger.info("微信回调支付通知结果�?" + result);
        Map<String, String> map = null;
        try {
            /**
             * 解析微信通知返回的信�?
             */
            map = XMLUtil.doXMLParse(result);
        } catch (JDOMException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        logger.info("- - - - - - - - -");
        logger.info("= = = = = = = = =:" + map);
        // 若支付成功，则告知微信服务器收到通知
        if (map.get("return_code").equals("SUCCESS")) {
            if (map.get("result_code").equals("SUCCESS")) {
                logger.info("微信充�?�会员体系成功！");
                String out_trade_no = map.get("out_trade_no");

                Order order = orderMapper.selectById(out_trade_no);
                if ("0".equals(order.getStatus())) {
                    order.setStatus("1");
                    order.setPaytime(DateUtils.formatTimeNow());
                    Address address = addressMapper.selectById(order.getAddressid());
                    Shop shop = shopMapper.selectById(order.getShopid());
                    sendsms1.sendSms(out_trade_no, address.getUsername(), shop.getTel(), order.getTotalmoney(), address.getTelephone());

                }

                orderMapper.updateById(order);

                // if(userorder!=null){
                //
                // }else{
                // logger.error("微信支付回调订单不存�?");
                // }
            } else {
                String out_trade_no = map.get("out_trade_no");

                logger.error("微信支付回调失败");
            }
        }
    }

    public static void main(String args[]) {
        String notifyStr = XMLUtil.setXML("SUCCESS", "");
        System.out.println(notifyStr);
    }
}
