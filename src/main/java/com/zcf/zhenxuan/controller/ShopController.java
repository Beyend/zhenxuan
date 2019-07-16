package com.zcf.zhenxuan.controller;


import com.zcf.zhenxuan.service.ShopService;
import com.zcf.zhenxuan.util.json.Body;
import com.zcf.zhenxuan.vo.in.UserParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author kmr123
 * @since 2018-12-18
 */
@Controller
@RequestMapping("/shop")
@ResponseBody
@CrossOrigin
public class ShopController {
    @Autowired
    ShopService shopService;

    /**
     * 新增店铺
     *
     * @param shopname
     * @param username
     * @param tel
     * @param address
     * @param account
     * @param pwd
     * @return
     */
    @PostMapping("/addshop")
    public Body addshop(String shopname, String username, String tel, String address, String account, String pwd, String cityid, String introduce) {
        return shopService.addshop(shopname, username, tel, address, account, pwd, cityid, introduce);
    }


    /**
     * 查看所有店铺
     *
     * @param param
     * @return
     */
    @PostMapping("/getshops")
    public Body getshops(UserParam param) {
        return shopService.getshops(param);
    }


    /**
     * 查看店铺详情
     *
     * @param shopid
     * @return
     */
    @PostMapping("/getshopinfo")
    public Body getshopinfo(String shopid, String userid) {
        return shopService.getshopinfo(shopid, userid);
    }

    /**
     * 下架店铺
     *
     * @param shopid
     * @return
     */
    @PostMapping("/deleteshop")
    public Body deleteshop(String shopid) {
        return shopService.deleteshop(shopid);
    }

    /**
     * 上架商家
     *
     * @param shopid
     * @return
     */
    @PostMapping("/upshop")
    public Body upshop(String shopid) {
        return shopService.upshop(shopid);
    }

    /**
     * 根据商家后台查看商家店铺
     *
     * @param adminid
     * @return
     */
    @PostMapping("/getshopbyadmin")
    public Body getshopbyadmin(String adminid) {
        return shopService.getshopbyadmin(adminid);
    }

    /**
     * 修改店铺信息
     *
     * @param shopid
     * @param shoplogo
     * @param introduce
     * @param shopimg
     * @param shopname
     * @return
     */
    @PostMapping("/updateshop")
    public Body updateshop(String shopid, String shoplogo, String introduce, String shopimg, String shopname) {
        return shopService.updateshop(shopid, shoplogo, introduce, shopimg, shopname);
    }

    @PostMapping("/getadminshop")
    public Body getadminshop(String adminid) {
        return shopService.getadminshop(adminid);
    }

    @PostMapping("/shanchuShop")
    public Body shanchuShop(String shopid) {
        return  shopService.shanchuShop(shopid);
    }

}

