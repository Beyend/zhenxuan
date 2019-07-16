package com.zcf.zhenxuan.controller;


import com.zcf.zhenxuan.service.CityService;
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
@RequestMapping("/city")
@ResponseBody
@CrossOrigin
public class CityController {
    @Autowired
    CityService cityService;

    /**
     * 添加城市
     *
     * @param cityname
     * @return
     */
    @PostMapping("/addcity")
    public Body addcity(String cityname) {
        return cityService.addcity(cityname);
    }

    /**
     * 更改城市信息
     *
     * @param cityid
     * @param cityname
     * @param cityid
     * @return
     */
    @PostMapping("/updatecity")
    public Body updatecity(String cityid, String cityname) {
        return cityService.updatecity(cityid, cityname);
    }

    /**
     * 查看城市列表
     *
     * @return
     */
    @PostMapping("/getcitys")
    public Body getcitys() {
        return cityService.getcitys();
    }

    /**
     * 删除城市
     *
     * @param cityid
     * @return
     */
    @PostMapping("/deletecity")
    public Body deletecity(String cityid) {
        return cityService.deletecity(cityid);
    }

    /**
     * 查看城市详情
     *
     * @param cityid
     * @return
     */
    @PostMapping("/getcityinfo")
    public Body getcityinfo(String cityid) {
        return cityService.getcityinfo(cityid);
    }

}

