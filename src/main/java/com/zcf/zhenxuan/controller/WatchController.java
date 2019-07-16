package com.zcf.zhenxuan.controller;


import com.zcf.zhenxuan.service.WatchService;
import com.zcf.zhenxuan.util.json.Body;
import com.zcf.zhenxuan.vo.in.UserParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
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
@RequestMapping("/watch")
@ResponseBody
@CrossOrigin
public class WatchController {
    @Autowired
    WatchService watchService;
    /**
     * 查看用户的同城的浏览历史
     * @return
     */
    @PostMapping("/getwatchlist")
    public Body getwatchlist(UserParam param){
       return watchService.getwatchlist(param);
    }
    /**
     * 查看用户的商品的浏览历史
     * @return
     */
    @PostMapping("/getwatchlistgoods")
    public Body getwatchlistgoods(UserParam param){
        return watchService.getwatchlistgoods(param);
    }




}

