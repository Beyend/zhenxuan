package com.zcf.zhenxuan.controller;


import com.zcf.zhenxuan.service.CollectionService;
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
@RequestMapping("/collection")
@ResponseBody
@CrossOrigin
public class CollectionController {
    @Autowired
    CollectionService collectionService;

    /**
     * 添加收藏
     *
     * @param goodsid
     * @param userid
     * @return
     */
    @PostMapping("/addcollection")
    public Body addcollection(String goodsid, String userid) {
        return collectionService.addcollection(goodsid, userid);
    }

    /**
     * 取消收藏
     *
     * @param collectionid
     * @return
     */
    @PostMapping("/canclecollectiono")
    public Body canclecollectiono(String userid,String  goodsid) {
        return collectionService.canclecollectiono(userid,goodsid);
    }

    /**
     * 查看用户的收藏列表
     * @param param
     * @return
     */
    @PostMapping("/getcollections")
    public Body getcollections(UserParam param) {
        return  collectionService.getcollections(param);
    }

}

