package com.zcf.zhenxuan.controller.pay.AliPays;

/**
 * 支付宝支付所需的必要参数 理想情况下只需配置此处
 */
public class ALiPayConfig {
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static String partner ="2088431247725207";

	// appid
	public static String appid = "2019012363141656";

	// 商户支付宝账号
	public static String seller_email ="zxyp007@163.com";

	// 商户真实姓名
	public static String account_name = "徐州臻选优品网络科技有限公司";

	// URL
	public static String url = "https://openapi.alipay.com/gateway.do";
	public static String timeoutExpress = "1000";

	// 商户的私钥RSA
	public static String private_key = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCRx26qm+RN84PhZRbTG0WDvyxRntDqheoX6UPeG5Sd5bjZAMdf21G3S1lwmUW8wQJVzeINhbwEU2grURzNgDDMtaKupR+NdOV4CAJXisa8T3mGyAXfaM7R4rruhs/crfjMS2vdHF3JaVNzl83v1yKG96LNV3yt6wbdhbxVQ8u9G1dyNdvb/TZLkl3QROjNnhafmOy71jcQhxUKYPm4MV2z2Ni0X2dPH/lxrsXBFQBdNUL6K5IE3sri+IiJUn9zpJrxutHLs9LQW9vV1E04UUxzkKV/PBxNh8Gr4IUX18/UOHCZLTO1CCf+/fL3RP1A5kAxTxH82yuPtc9nQTcQiROjAgMBAAECggEBAIF9/ODR/1f68u/5Yu/9Lp+b5ptODtf0QoAg9edK6pXjpHImjfOpFwpRhU5O6DKF5DQh4SYP4HabPFIWCBbHQxHkWnD2PnPiHZ+kxafLn0NQ+WtvLEBKkkTAOTp+SBAH/ZRDfrn3Y5vw0m9Arm7Y1FO9j0EF8LtwpTWLgULgKQ9960cD8xnVQGlJN0ABIN8pEbduszRDP1oqewN+wKlTJoDiseQB0YD9T7xUH3b4oK+R64i2kUTYdWcIJ0+hN+EB6mSGlMaRp5z29RUDJbPyeT0gVLYJGFLqgDi4ZS+QVTMAK0jH7z0TaK3w6assvtE5DDRYpW+73BkhS4bNT3QyYfkCgYEA2k0nW+QYL+Rz2mGV837gNGap2RC2E3PMrVu98yfG2fZszjcmQmB1l2QMRKYZccY9nDbdX5sC/3tPYiUMEY2+tSkdznJRCmwn2Erkp8cmZlpEouYiY+SBbYa+VoAG02IOnmG6EPS7N9xNJ+OsmhfJuiiLKhk5FwE8WX913btNtl0CgYEAqvQmsKHG6O/vg9qCHhfSW+PXMPXXYmFU+v+OXHOB/lrkFa6c3AtxjREUIcxTBS0PVxqc4Z734VeD3dRjCGGFb9UJTw0j6BlYuDEbh7OPZNYXiRx/ljBdInPRzPgJHML7tlLSu5ThmcjpxxZvKTBHqT0j2evMR4TlHfkHYMRRUf8CgYEAhNUJrAqBBTNXo3xZyA5d0sTdeO2IPRHra1k1ne2IPDyMPHamWyFirDn+lQ8U0Hw2JQXlcjVaf6bCyQ+N5mh/sLBSKLq6wULKg/weYlD19rXaqdyq9Gmor8Kbsvq4LIuwJYOFuqeP/A+bCzioYCM1ZochFv6+FFd2J2c9iyxbvykCgYEAhKnXZHcGzzlzW5U0KHpACoMzOZFuRbBWrDmplmEk3ehW2+SXYcOXqOs5/kh7nKatIuFKIWSl5D9v27O37ocPcAuYn7oIxVL2IGorfRGBE7V4qjRtZnW9sMDEbRWGrMU7ZO+9DUiqEhdyI8drGEGZOuJxcemkq1OcIbhhW1y95q0CgYA9KErPqHrIaJMJS0qVrt3oVfrgHzItPGSwyi7apyihY8TGkm7s4Lye6RPKvswni5MFLKujnGkxYM9X0EFTwgG8D3XXidQH24MAiZLpveQ9y7DguzSDjl8VSIAN11qK09lXdbJFzw0ExY2msYGL672hCqH6bNOpSx47rPSKoWVTRA==";
	// 支付宝的公钥 RSA
	public static String ali_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAg2DU/4BVosL+KGT4Q4KmHBvRc5k0ZYitz1QSOWV6tK2lHI0x2AwPG5Sg1k3kijcF80D+7YaHSBEqB5mIg1B4j7HTArHK6zt5YusWGQpY/zsi6i+kt+zn+bUsVus6oNlHFLkAwdq4YC3Qh/dE1E6tcWwg0LEjDXyNTnW4KHMHHxmEu/uRUV9ND/dxADOwvLqgQYxonLO1Y3TX5oy0baWvOeDJ6eIySt32ZZxusI6XEvIKC7NA2cF78Gro6+lxLDFKvhqTeeSwB59oLrV5Blhw/QSyNda5mieaxxerNrzaypUsbSnGzMxnxpMfeH9WCGB78mgY0+cnlucY2tdsuSbMBQIDAQAB";
	// 签名方式 (支付回调签名方式)
	public static String pay_sign_type = "RSA2";

	/**
	 * 支付宝服务器主动通知商户服务器里指定的页面http/https路径。建议商户使用https
	 * 这里需要测试的话，需要用外网测试。https://www.ngrok.cc/ 这里有免费和付费的，实际上，免费用一下就可以了。
	 */
	public static String notify_url = "http://47.99.94.250:80/alipay/notify_url";
	// 商品的标题/交易标题/订单标题/订单关键字等。
	public static String subject = "商品购买";

	// ↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

	// 接口名称 固定为：alipay.trade.app.pay
	public static String method = "alipay.trade.app.pay";

	// 调用的接口版本，固定为：1.0
	public static String version = "1.0";

	// 销售产品码，商家和支付宝签约的产品码，为固定值QUICK_MSECURITY_PAY
	public static String product_code = "QUICK_MSECURITY_PAY";

	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";

}
