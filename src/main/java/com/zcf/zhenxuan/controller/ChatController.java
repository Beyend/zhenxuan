package com.zcf.zhenxuan.controller;


import com.zcf.zhenxuan.service.ChatService;
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
@RequestMapping("/chat")
@ResponseBody
@CrossOrigin
public class ChatController {
    @Autowired
    ChatService chatService;

    /**
     * 聊天
     *
     * @param shopid
     * @param userid
     * @param type
     * @param content
     * @param img
     * @return
     */
    @PostMapping("/addchat")
    public Body addchat(String shopid, String userid, String type, String content, String img) {
        return chatService.addchat(shopid, userid, type, content, img);
    }

    /**
     * 聊天记录
     *
     * @param shopid
     * @param userid
     * @return
     */
    @PostMapping("/getchatlist")
    public Body getchatlist(String shopid, String userid) {
        return chatService.getchatlist(shopid, userid);
    }

    /**
     * 用户的聊天窗口列表
     *
     * @param userid
     * @return
     */
    @PostMapping("/chatlistByuserid")
    public Body chatlistByuserid(String userid) {
        return chatService.chatlistByuserid(userid);
    }

    /**
     * 店铺得聊天窗口列表
     *
     * @param shopid
     * @return
     */
    @PostMapping("/chatlistByshopid")
    public Body chatlistByshopid(String shopid) {
        return chatService.chatlistByshopid(shopid);
    }


}

