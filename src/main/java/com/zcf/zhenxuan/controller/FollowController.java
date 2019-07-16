package com.zcf.zhenxuan.controller;


import com.zcf.zhenxuan.service.FollowService;
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
@RequestMapping("/follow")
@ResponseBody
@CrossOrigin
public class FollowController {

    @Autowired
    FollowService followService;

    /**
     * 添加关注店铺
     *
     * @param shopid
     * @param userid
     * @return
     */
    @PostMapping("/addfollow")
    public Body addfollow(String shopid, String userid) {
        return followService.addfollow(shopid, userid);
    }

    /**
     * 查看用户得关注列表
     *
     * @param param
     * @return
     */
    @PostMapping("/getfollowlist")
    public Body getfollowlist(UserParam param) {
        return followService.getfollowlist(param);
    }

    /**
     * 取消关注
     *
     * @param followid
     * @return
     */
    @PostMapping("/canclefollow")
    public Body canclefollow(String userid,String  shopid) {
        return followService.canclefollow(userid,shopid);
    }
}

