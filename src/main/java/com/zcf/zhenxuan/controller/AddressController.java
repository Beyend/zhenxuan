package com.zcf.zhenxuan.controller;


import com.zcf.zhenxuan.service.AddressService;
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
@RequestMapping("/address")
@ResponseBody
@CrossOrigin
public class AddressController {
    @Autowired
    AddressService addressService;

    /**
     * 添加地址
     *
     * @param userid
     * @param area
     * @param detailaddress
     * @param username
     * @param telephone
     * @param type
     * @return
     */
    @PostMapping("/addaddress")
    public Body addaddress(String userid, String area, String detailaddress, String username, String telephone, String type) {
        return addressService.addaddress(userid, area, detailaddress, username, telephone, type);
    }

    /**
     * 修改地址
     *
     * @param addressid
     * @param area
     * @param detailaddress
     * @param username
     * @param telephone
     * @param type
     * @return
     */
    @PostMapping("/updateaddress")
    public Body updateaddress(String addressid, String area, String detailaddress, String username, String telephone, String type) {
        return addressService.updateaddress(addressid, area, detailaddress, username, telephone, type);
    }

    /**
     * 删除地址
     *
     * @param addressid
     * @return
     */
    @PostMapping("deleteaddress")
    public Body deleteaddress(String addressid) {
        return addressService.deleteaddress(addressid);
    }

    /**
     * 查看地址详情
     *
     * @param addressid
     * @return
     */
    @PostMapping("/getaddress")
    public Body getaddress(String addressid) {
        return addressService.getaddress(addressid);
    }

    /**
     * 查看用户的地址列表
     * @param userid
     * @return
     */
    @PostMapping("/getaddressList")
    public Body getaddressList(String userid) {
        return addressService.getaddressList(userid);
    }





}

