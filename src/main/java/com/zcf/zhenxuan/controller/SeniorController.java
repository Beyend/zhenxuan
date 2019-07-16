package com.zcf.zhenxuan.controller;


import com.zcf.zhenxuan.service.SeniorService;
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
@RequestMapping("/senior")
@ResponseBody
@CrossOrigin
public class SeniorController {
    @Autowired
    SeniorService seniorService;


    /**
     * 添加高级申请
     *
     * @param userid
     * @param relname
     * @param reltel
     * @param address
     * @param idcard
     * @param business
     * @return
     */
    @PostMapping("/addsenior")
    public Body addsenior(String userid, String relname, String reltel, String address, String idcard, String business) {
        return seniorService.addsenior(userid, relname, reltel, address, idcard, business);
    }

    /**
     * 查看高级审核列表
     *
     * @param type
     * @return
     */
    @PostMapping("/getseniorlist")
    public Body getseniorlist(String type) {
        return seniorService.getseniorlist(type);
    }

    /**
     * 审核 高级认证
     *
     * @param seniorid
     * @param check
     * @return
     */
    @PostMapping("/checksenior")
    public Body checksenior(String seniorid, String check) {
        return seniorService.checksenior(seniorid, check);
    }

    /**
     * 查看是否初级认证过
     * @param userid
     * @return
     */
    @PostMapping("/issenior")
    public  Body  issenior(String userid){
        return seniorService.issenior(userid);
    }

}

