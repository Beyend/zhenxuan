package com.zcf.zhenxuan.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.security.KeyStore;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.net.ssl.SSLContext;

import com.zcf.zhenxuan.bean.Order;
import com.zcf.zhenxuan.controller.pay.AliPays.ALiPayConfig;
import com.zcf.zhenxuan.controller.pay.WXpay.*;
import com.zcf.zhenxuan.mapper.OrderMapper;
import com.zcf.zhenxuan.util.Hutool;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
//import org.apache.http.ssl.SSLContexts;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


public class AliAndWXRefund {

    public void main(String[] args) throws Exception {

        wxRefund(0.01, "15535854858663459771");
    }

    /**
     * 微信退款
     *
     * @param rdfund
     * @param req
     * @param type   1-公众号 2-小程序 3-app
     * @return
     * @throws Exception
     */
//	   public static Map<String, Object>  refundUtil(RefundModel  rdfund
//	           , HttpServletRequest req,Integer type) throws Exception{
//	       Map<String, Object> mp = new HashMap<String, Object>();
//	       //设置支付账户信息
//	        String MchKey = ""; //商户号秘钥
//	        String MchId = ""; //商户号id
//	        rdfund.setAppid(ConstantUtil.APP_ID);
//	        rdfund.setMch_id(ConstantUtil.MCH_ID); 
//	        MchKey = ConstantUtil.APP_KEY;
//	        MchId = ConstantUtil.MCH_ID;
//	     
//
//	       rdfund.setNonce_str(Hutool.createId());
//	       rdfund.setSign_type("MD5");
//	       rdfund.setSign(SignUtil.sign(SignUtil.createRefundSign(rdfund),MchKey));
//	       
//	       try{
//	           XMLUtil xmlUtil= new XMLUtil();
//	           xmlUtil.xstream().alias("xml", rdfund.getClass());
//	          
//	           String xml = xmlUtil.xstream().toXML(rdfund);
//	           String response = PayUtil.ssl(BasicInfo.refundurl, xml,req,MchId);
//	           System.out.println(response);
//	           Map<String, String> map = xmlUtil.parseXml(response);
//	           if("SUCCESS".equals(map.get("result_code"))){
//	                mp.put("stu", true);
//	                return mp;
//	           }else{
//	             mp.put("stu", false);
//	             mp.put("errMsg", map.get("return_msg"));
//	             mp.put("errDes", map.get("err_code_des"));
//	             log.error("微信退款失败》》"+"错误码:"+map.get("return_msg")+"  ;"
//	                                + "描述:"+map.get("err_code_des"));
//	             return mp;
//	           }
//	      }catch (Exception e) {
//	             log.error("微信退款异常》》"+e);
//	             throw new MyException(SystemError.WX_RETREIEV_ERROR.getCode(), 
//	                   SystemError.WX_RETREIEV_ERROR.getMessage());
//	      }
//	   }

    /**
     * 支付宝退款
     *
     * @param money
     * @param out_trade_no
     * @return
     * @throws AlipayApiException
     */
    public int refundMoney(Double money, String out_trade_no) throws AlipayApiException {

        DecimalFormat dex = new DecimalFormat("#0.00");
        String payAccount = String.valueOf(dex.format(money));// 测试使用（可删除）
        Integer randomNumber = new Random().nextInt(900) + 100;
        String out_request_no = Hutool.getTime() + randomNumber;
//		String out_request_no=StringHideUtils.getOrderId("PT");
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", ALiPayConfig.appid,
                ALiPayConfig.private_key, "json", ALiPayConfig.input_charset, ALiPayConfig.ali_public_key, "RSA2");
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        request.setBizContent("{" + "\"out_trade_no\":\"" + out_trade_no + "\"," + // 商户订单号
                // 也可使用支付宝交易号trade_no这个参数，两个必填一个
                "\"out_request_no\":\"" + out_request_no + "\"," + // 部分退款必须
                "\"refund_amount\":\"" + payAccount + "\"," + // 退款金额
                "\"refund_reason\":\"正常退款\"" + "  }");
        AlipayTradeRefundResponse response = alipayClient.execute(request);
        if (response.isSuccess()) {


            return 1;
        } else {
            return 2;
        }
    }

    /**
     * 微信APP退款
     *
     * @return
     * @throws Exception
     */
    public int wxRefund(Double money, String out_trade_no) throws Exception {
        Integer randomNumber = new Random().nextInt(900) + 100;
        String out_request_no = Hutool.getTime() + randomNumber;
        DecimalFormat df = new DecimalFormat("#0.00");
        SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
        parameters.put("appid", ConstantUtil.APP_ID);// appid
        parameters.put("mch_id", ConstantUtil.MCH_ID);// 商户号
        parameters.put("nonce_str", WXUtil.getNonceStr());
        parameters.put("out_trade_no", out_trade_no);// 订单号
        parameters.put("out_refund_no", out_request_no);// 我们自己设定的退款申请号，约束为UK
        String totalPrice = String.valueOf((int) (money * 100));
        parameters.put("total_fee", totalPrice);// 单位为分
        parameters.put("refund_fee", totalPrice);// 单位为分
        // parameters.put("op_user_id", ConstantUtil);//操作人员,默认为商户账号
        String sign = PrepayIdRequestHandler.createSign("utf-8", parameters);
        parameters.put("sign", sign);

        String reuqestXml = XMLUtil.getRequestXml(parameters);
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        FileInputStream instream = new FileInputStream(new File("C:/oulWV_certificate/apiclient_cert.p12"));// 放退款证书的路径
//		FileInputStream instream = new FileInputStream(
//				new File("/home/wwwroot/web/oulWV_certificate/apiclient_cert.p12"));// 放退款证书的路径
        try {
            keyStore.load(instream, ConstantUtil.MCH_ID.toCharArray());// 商户号
        } finally {
            instream.close();
        }

        SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, ConstantUtil.MCH_ID.toCharArray())
                .build();// 商户号
        @SuppressWarnings("deprecation")
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[]{"TLSv1"}, null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
        try {

            HttpPost httpPost = new HttpPost("https://api.mch.weixin.qq.com/secapi/pay/refund");// 退款接口

            StringEntity reqEntity = new StringEntity(reuqestXml);
            // 设置类型
            reqEntity.setContentType("application/x-www-form-urlencoded");
            httpPost.setEntity(reqEntity);
            CloseableHttpResponse response = httpclient.execute(httpPost);
            try {
                HttpEntity entity = response.getEntity();

                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());
                if (entity != null) {
                    System.out.println(entity.getContent());
                    BufferedReader bufferedReader = new BufferedReader(
                            new InputStreamReader(entity.getContent(), "UTF-8"));
                    String text = "";
                    String str = "";
                    while ((text = bufferedReader.readLine()) != null) {
                        str += text + "\n";
                    }
                    System.out.println(str);
                    Map<String, String> map = ReturnXmlUtil.readStringXmlOut(str);
                    if ("SUCCESS".equals(map.get("return_code")) && "SUCCESS".equals(map.get("result_code"))) {

                        return 1;
                    } else {
                        return 2;
                    }
                }
                EntityUtils.consume(entity);
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
        return 2;
    }
    /**
     * 微信小程序退款
     *
     * @param id
     * @return
     * @throws Exception
     */
//	public static int wxAppletRefund(Double money,String out_trade_no) throws Exception{
//		String appid = "wxa645e700cc1ad7b8";  // 公众号appid
////		 String appsecret = "fbe2a13954cb483c67d6e946a47538f8"; // 公众号秘钥
////		 String partnerkey = "end15928016819end15928016819yess"; // 商户API秘钥
//		 String partner = "1523274761"; // 商户号
//		DecimalFormat df = new DecimalFormat("#0.00");
//		SortedMap<Object,Object> parameters = new TreeMap<Object,Object>();
//		parameters.put("appid", appid);//appid
//		parameters.put("mch_id", partner);//商户号
//		parameters.put("nonce_str", WXUtil.getNonceStr());
//		parameters.put("out_trade_no", out_trade_no);//订单号
//		parameters.put("out_refund_no", StringHideUtils.getOrderId("TK"));//我们自己设定的退款申请号，约束为UK
//		String totalPrice =String.valueOf(Integer.parseInt(df.format(money))*100);
//		parameters.put("total_fee", totalPrice) ;//单位为分
//		parameters.put("refund_fee", totalPrice);//单位为分
//		//parameters.put("op_user_id", ConstantUtil);//操作人员,默认为商户账号
//		String sign = PrepayIdRequestHandler.createSign1("utf-8", parameters);
//		parameters.put("sign", sign);
//		
//		String reuqestXml = XMLUtil.getRequestXml(parameters);
//		KeyStore keyStore  = KeyStore.getInstance("PKCS12");
//		FileInputStream instream = new FileInputStream(new File("C:/apiclient_cert.p12"));//放退款证书的路径
//		try {
//			keyStore.load(instream, partner.toCharArray());//商户号
//		} finally {
//			instream.close();
//		}
//		
//		SSLContext sslcontext = SSLContexts.custom()
//				.loadKeyMaterial(keyStore, partner.toCharArray()).build();//商户号
//		@SuppressWarnings("deprecation")
//		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
//				sslcontext,
//				new String[] { "TLSv1" },
//				null,
//				SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
//		CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
//		try {
//			
//			HttpPost httpPost = new HttpPost("https://api.mch.weixin.qq.com/secapi/pay/refund");//退款接口
//			
//			StringEntity  reqEntity  = new StringEntity(reuqestXml);
//			// 设置类型
//			reqEntity.setContentType("application/x-www-form-urlencoded");
//			httpPost.setEntity(reqEntity);
//			CloseableHttpResponse response = httpclient.execute(httpPost);
//			try {
//				HttpEntity entity = response.getEntity();
//				
//				System.out.println("----------------------------------------");
//				System.out.println(response.getStatusLine());
//				if (entity != null) {
//					System.out.println(entity.getContent());
//					BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent(),"UTF-8"));
//					String text="";
//					String str="";
//					while ((text=bufferedReader.readLine()) != null) {
//						str+=text+"\n";
//					}
//					System.out.println(str);
//					Map<String,String> map= ReturnXmlUtil.readStringXmlOut(str);
//					if("SUCCESS".equals(map.get("return_code")) && "SUCCESS".equals(map.get("result_code"))){
//						return 1;
//					}else{
//						return 2;
//					}
//				}
//				EntityUtils.consume(entity);
//			} finally {
//				response.close();
//			}
//		} finally {
//			httpclient.close();
//		}
//		return 2;
//	}
//	
//	
////	public static int refundOrder(HttpServletRequest request, HttpServletResponse response, Orders order, String price) {
////
////		// 商户相关资料
////		 String appid = "wxa645e700cc1ad7b8";  // 公众号appid
////		 String appsecret = "fbe2a13954cb483c67d6e946a47538f8"; // 公众号秘钥
////		 String partnerkey = "end15928016819end15928016819yess"; // 商户API秘钥
////		 String partner = "1523274761"; // 商户号
////		 String notify_url = "https://api.mch.weixin.qq.com/pay/unifiedorder"; // 退款回调地址
////		// 用于获取随机数
////		String currTime = TenpayUtil.getCurrTime();// 获取当前时间
////		String strTime = currTime.substring(8, currTime.length());// 8位日期
////		String strRandom = TenpayUtil.buildRandom(4) + "";// 四位随机数
////		String strReq = strTime + strRandom;// 10位序列号,可以自行调整
////		// 设置随机字符串
////		String nonce_str = strReq;
////		// 设置商户退款单号
////		Integer randomNumber = new Random().nextInt(900) + 100;
////		String orderIncrementId = StringHideUtils.getOrderId("TK");
////		String out_trade_no = order.getId() + "";
////		// 总金额以分为单位，不带小数点
////		int total_fee = (int) (order.getTotal().doubleValue() * 100);
////		// 商户号
////		String mch_id = partner;
////		int refund_fee = (int) (Double.parseDouble(price) * 100);
////		// 创建hashmap(用户获得签名)
////		SortedMap<String, String> packageParams = new TreeMap<String, String>();
////		packageParams.put("appid", appid);
////		packageParams.put("mch_id", partner);
////		packageParams.put("nonce_str", nonce_str);
////		packageParams.put("out_trade_no", out_trade_no);
////		packageParams.put("out_refund_no", orderIncrementId);
////		packageParams.put("total_fee", total_fee + "");
////		packageParams.put("refund_fee", refund_fee + "");
////		packageParams.put("notify_url", notify_url);
////
////		RequestHandler reqHandler = new RequestHandler(request, response);
////		reqHandler.init(appid, appsecret, partnerkey);
////		String sign = reqHandler.createSign(packageParams);
////
////		String xml = "<xml>" + "<appid>" + appid + "</appid>" + "<mch_id>" + partner + "</mch_id>" + "<nonce_str>"
////				+ nonce_str + "</nonce_str>" + "<sign>" + sign + "</sign>" + "<out_refund_no>" + orderIncrementId
////				+ "</out_refund_no>" + "<out_trade_no>" + out_trade_no + "</out_trade_no>" + "<refund_fee>" + refund_fee
////				+ "</refund_fee>" + "<total_fee>" + total_fee + "</total_fee>" + "<notify_url>" + notify_url
////				+ "</notify_url>" + "</xml>";
////
////		RefundVo rv = new RefundVo();
////		try {
////			// 发送请求(POST)(获得数据包ID)(这有个注意的地方 如果不转码成ISO8859-1则会告诉你body不是UTF8编码
////			// 就算你改成UTF8编码也一样不好使 所以修改成ISO8859-1)
////			System.out.println("xml>>>" + doRefund(ConstantUtil.REFUND_URL, new String(xml.getBytes(), "ISO8859-1")));
////			Map<String, String> map = XMLUtil
////					.doXMLParse(doRefund(ConstantUtil.REFUND_URL, new String(xml.getBytes(), "ISO8859-1")));
////			// 应该创建 退款表数据
////			if (map != null
////					&& (StringUtils.isNotBlank(map.get("return_code")) && "SUCCESS".equals(map.get("return_code")))) {
////				if (StringUtils.isBlank(map.get("err_code_des"))) {
////					// 接口调用成功 执行操作逻辑 返回成功状态码给前台
////					return 1;
////				} else {
////					// resultJson.put("returnCode", "error");
////					// resultJson.put("err_code_des", map.get("err_code_des"));
////					rv.setReturnCode("error");
////					rv.setErrCodeDes(map.get("err_code_des"));
////				}
////			} else {
////				// resultJson.put("returnCode", map.get("return_code"));
////				// resultJson.put("err_code_des", map.get("err_code_des"));
////				rv.setReturnCode(map.get("return_code"));
////				rv.setErrCodeDes(map.get("err_code_des"));
////			}
////		} catch (UnsupportedEncodingException e) {
////			e.printStackTrace();
////		} catch (Exception e) {
////			e.printStackTrace();
////		}
////		System.out.println(rv.getErrCodeDes());
////		return 2;
////	}
//
////	private String doRefund(String url, String data) throws Exception {
////		// 商户相关资料
////				 String appid = "wxa645e700cc1ad7b8";  // 公众号appid
////				 String appsecret = "fbe2a13954cb483c67d6e946a47538f8"; // 公众号秘钥
////				 String partnerkey = "end15928016819end15928016819yess"; // 商户API秘钥
////				 String partner = "1523274761"; // 商户号
////				 String notify_url = "https://api.mch.weixin.qq.com/pay/unifiedorder"; // 退款回调地址
////		/**
////		 * 注意PKCS12证书 是从微信商户平台-》账户设置-》 API安全 中下载的
////		 */
////
////		KeyStore keyStore = KeyStore.getInstance("PKCS12");
////		// 证书存放路径
////		String path = "C:\\Users\\Amarisa\\Desktop\\water\\water\\src\\main\\resources\\refund_certificate\\";
////
////		FileInputStream instream = new FileInputStream(path + "apiclient_cert.p12");// P12文件目录
////
////		try {
////			/**
////			 * 此处要改
////			 */
////			keyStore.load(instream, partner.toCharArray());// 这里写密码..默认是你的MCHID
////		} finally {
////			instream.close();
////		}
////
////		// Trust own CA and all self-signed certs
////		/**
////		 * 此处要改
////		 */
////		SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, partner.toCharArray())// 这里也是写密码的
////				.build();
////		// Allow TLSv1 protocol only
////		@SuppressWarnings("deprecation")
////		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null,
////				SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
////		CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
////		try {
////			HttpPost httpost = new HttpPost(url); // 设置响应头信息
////			httpost.addHeader("Connection", "keep-alive");
////			httpost.addHeader("Accept", "*/*");
////			httpost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
////			httpost.addHeader("Host", "api.mch.weixin.qq.com");
////			httpost.addHeader("X-Requested-With", "XMLHttpRequest");
////			httpost.addHeader("Cache-Control", "max-age=0");
////			httpost.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
////			httpost.setEntity(new StringEntity(data, "UTF-8"));
////			CloseableHttpResponse response = httpclient.execute(httpost);
////			try {
////				HttpEntity entity = response.getEntity();
////
////				String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
////				EntityUtils.consume(entity);
////				System.out.println(">>>>>>>" + jsonStr);
////				return jsonStr;
////			} finally {
////				response.close();
////			}
////		} finally {
////			httpclient.close();
////		}
////	}
//	

}
