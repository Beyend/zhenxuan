package com.zcf.zhenxuan.controller;


import com.zcf.zhenxuan.service.BrowseService;
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
@RequestMapping("/browse")
@ResponseBody
@CrossOrigin
public class BrowseController {
    @Autowired
    BrowseService browseService;
    /**
     * 添加反馈
     *
     * @param content
     * @param img
     * @param userid
     * @param username
     * @param lianxi
     * @return
     */
    @PostMapping("/addbrowse")
    public Body addbrowse(String content, String img, String userid, String username, String lianxi) {
        return browseService.addbrowse(content,img,userid,username,lianxi);
    }

    /**
     * 查看 反馈分页
     * @param param
     * @return
     */
    @PostMapping("/getbrowse")
    public  Body  getbrowse(UserParam param){
        return  browseService.getbrowse(param);
    }
}

