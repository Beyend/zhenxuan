package com.zcf.zhenxuan.controller;


import com.zcf.zhenxuan.service.PrimaryService;
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
@RequestMapping("/primary")
@ResponseBody
@CrossOrigin
public class PrimaryController {
    @Autowired
    PrimaryService primaryService;

    /**
     * 添加初级认证
     *
     * @param userid
     * @param relname
     * @param reltel
     * @param address
     * @return
     */
    @PostMapping("/addprimary")
    public Body addprimary(String userid, String relname, String reltel, String address) {
        return primaryService.addprimary(userid, relname, reltel, address);
    }

    /**
     * 审核初级认证
     *
     * @param primaryid
     * @param check
     * @return
     */
    @PostMapping("/checkprimary")
    public Body checkprimary(String primaryid, String check) {
        return primaryService.checkprimary(primaryid, check);
    }

    /**
     * 查看审核列表
     *
     * @param type
     * @return
     */
    @PostMapping("/getprimary")
    public Body getprimary(String type) {
        return primaryService.getprimary(type);
    }

    /**
     *查看是否通过初级认证
     * @param userid
     * @return
     */
    @PostMapping("/isprimary")
    public  Body  isprimary(String userid){
        return primaryService.isprimary(userid);
    }
}

