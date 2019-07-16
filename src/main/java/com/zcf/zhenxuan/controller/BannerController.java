package com.zcf.zhenxuan.controller;


import com.zcf.zhenxuan.bean.Banner;
import com.zcf.zhenxuan.service.BannerService;
import com.zcf.zhenxuan.util.json.Body;
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
@RequestMapping("/banner")
@ResponseBody
@CrossOrigin
public class BannerController {
    @Autowired
    BannerService bannerService;

    /**
     * 添加轮播图
     *
     * @param cityid
     * @param bannerimg
     * @param goodsid
     * @param level
     * @return
     */
    @PostMapping("/addbanner")
    public Body addbanner(String cityid, String bannerimg, String goodsid, String level) {
        return bannerService.addbanner(cityid, bannerimg, goodsid, level);
    }

    /**
     * 更改轮播图位置
     *
     * @param bannerid
     * @param level
     * @return
     */
    @PostMapping("/changelevel")
    public Body changelevel(String bannerid, String level) {
        return bannerService.changelevel(bannerid, level);
    }

    /**
     * 查看轮播图详情
     *
     * @param bannerid
     * @return
     */
    @PostMapping("/getbannerinfo")
    public Body getbannerinfo(String bannerid) {
        return bannerService.getbannerinfo(bannerid);
    }

    /**
     * 删除轮播图
     *
     * @param bannerid
     * @return
     */
    @PostMapping("/deletebanner")
    public Body deletebanner(String bannerid) {
        return bannerService.deletebanner(bannerid);
    }

    /**
     * 查看城市banner图
     *
     * @param cityid
     * @return
     */
    @PostMapping("/getbannerlist")
    public Body getbannerlist(String cityid) {
        return  bannerService.getbannerlist(cityid);
    }

}

