package com.zcf.zhenxuan.controller.pay.WXpay;

public class ConstantUtil {
	/**
	 * 微信开发平台应用ID
	 */
	public static final String APP_ID = "wx328e42744e1d882d";
	/**
	 * 应用对应的AppSecret
	 */
	public static final String APP_SECRET = "09f30518753fa2e0d18e9c74bbf8349a";
	/**
	 * APP_KEY 商户平台---api安全---密钥
	 */
	public static final String APP_KEY = "51zhenxuan51zhenxuan51zhenxuan51";
	/**
	 * 微信支付商户号
	 */
	public static final String MCH_ID = "1530761861";
	/**
	 * 商品描述
	 */
	public static final String BODY = "商品支付";
	/**
	 * 商户号对应的密钥 也是 商户平台---api安全---密钥 同上面那个APP_KEY
	 */
	public static final String PARTNER_key = "51zhenxuan51zhenxuan51zhenxuan51";

	/**
	 * 商户id 同微信支付商户号
	 */
	public static final String PARTNER_ID = "1530761861";
	/**
	 * 常量固定值
	 */
	public static final String GRANT_TYPE = "client_credential";
	/**
	 * 获取预支付id的接口url
	 */
	public static String GATEURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	/**
	 * 微信服务器回调通知url
	 */
	//测试
	//public static String NOTIFY_URL = "192.168.31.88:8080/works/order/wx/notify_url";
	//服务器
	public static String NOTIFY_URL = "http://47.99.94.250:80/weixin/wx/notify_url";

	//public static String NOTIFY_RECHARGE_URL = "http://47.100.110.119:8089/works/order/wx/notify_recharge_url";
}