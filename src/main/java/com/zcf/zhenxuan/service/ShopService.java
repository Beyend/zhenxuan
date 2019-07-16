package com.zcf.zhenxuan.service;

import com.zcf.zhenxuan.bean.Shop;
import com.baomidou.mybatisplus.service.IService;
import com.zcf.zhenxuan.util.json.Body;
import com.zcf.zhenxuan.vo.in.UserParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kmr123
 * @since 2018-12-18
 */
public interface ShopService extends IService<Shop> {

    Body getshops(UserParam param);

    Body getshopinfo(String shopid,String userid);

    Body deleteshop(String shopid);

    Body upshop(String shopid);

    Body addshop(String shopname, String username, String tel, String address, String account, String pwd, String cityid, String introduce);

    Body getshopbyadmin(String adminid);

    Body updateshop(String shopid, String shoplogo, String introduce, String shopimg, String shopname);

    Body getadminshop(String adminid);

    Body shanchuShop(String shopid);
}
