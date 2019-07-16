package com.zcf.zhenxuan.controller;


import com.zcf.zhenxuan.service.TitleService;
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
 * @since 2019-01-18
 */
@Controller
@RequestMapping("/title")
@CrossOrigin
@ResponseBody
public class TitleController {

    @Autowired
    TitleService titleService;
    /**
     * 添加 标题
     *
     * @return
     */
    @PostMapping("/addtitle")
    public Body addtitle(String content) {
        return titleService.addtitle(content);
    }

    /**
     * 更新标题
     * @param titleid
     * @param content
     * @return
     */
    @PostMapping("/updatetitle")
    public   Body  updatetitle(String titleid,String content){
        return titleService.updatetitle(titleid,content);
    }

    /**
     * 删除标题
     * @param titleid
     * @return
     */
    @PostMapping("/deletetitle")
    public  Body  deletetitle(String titleid){
        return titleService.deletetitle(titleid);
    }

    /**
     * 标题详情
     * @param titleid
     * @return
     */
    @PostMapping("/gettitleinfo")
    public  Body  gettitleinfo(String titleid){
        return titleService.gettitleinfo(titleid);
    }

    /**
     * 查看标题列表
     * @return
     */
    @PostMapping("/gettitlelist")
    public  Body  gettitlelist(){
        return titleService.gettitlelist();
    }
}

