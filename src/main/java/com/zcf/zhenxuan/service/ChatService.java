package com.zcf.zhenxuan.service;

import com.zcf.zhenxuan.bean.Chat;
import com.baomidou.mybatisplus.service.IService;
import com.zcf.zhenxuan.util.json.Body;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kmr123
 * @since 2018-12-18
 */
public interface ChatService extends IService<Chat> {

    Body addchat(String shopid, String userid, String type, String content, String img);

    Body getchatlist(String shopid, String userid);

    Body chatlistByuserid(String userid);

    Body chatlistByshopid(String shopid);
}
