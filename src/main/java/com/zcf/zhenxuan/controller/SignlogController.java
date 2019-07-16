package com.zcf.zhenxuan.controller;


import com.zcf.zhenxuan.service.SignlogService;
import com.zcf.zhenxuan.util.json.Body;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author kmr123
 * @since 2019-01-16
 */
@Controller
@RequestMapping("/signlog")
@CrossOrigin
@ResponseBody
public class SignlogController {
    @Autowired
    SignlogService  signlogService;

    /**
     * 查看签到记录
     * @param userid
     * @return
     */
    @PostMapping("/getSingBy")
    public Body  getSingBy(String userid){
       return  signlogService.getSingBy(userid);
    }

}

