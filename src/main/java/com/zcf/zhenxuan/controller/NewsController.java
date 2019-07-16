package com.zcf.zhenxuan.controller;


import com.zcf.zhenxuan.service.NewsService;
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
 * @since 2018-12-18
 */
@Controller
@RequestMapping("/news")
@ResponseBody
@CrossOrigin
public class NewsController {
    @Autowired
    NewsService newsService;

    /**
     * 查看某人的 系统消息
     * @param userid
     * @return
     */
    @PostMapping("/getnewslist")
    public Body getnewslist(String userid){
        return  newsService.getnewslist(userid);
    }
}

