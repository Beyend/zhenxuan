package com.zcf.zhenxuan.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.zcf.zhenxuan.bean.Admin;
import com.zcf.zhenxuan.bean.Chat;
import com.zcf.zhenxuan.bean.Shop;
import com.zcf.zhenxuan.bean.User;
import com.zcf.zhenxuan.mapper.ChatMapper;
import com.zcf.zhenxuan.mapper.ShopMapper;
import com.zcf.zhenxuan.mapper.UserMapper;
import com.zcf.zhenxuan.service.ChatService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zcf.zhenxuan.util.DateUtils;
import com.zcf.zhenxuan.util.Hutool;
import com.zcf.zhenxuan.util.json.Body;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author kmr123
 * @since 2018-12-18
 */
@Service
public class ChatServiceImpl extends ServiceImpl<ChatMapper, Chat> implements ChatService {

    @Autowired
    ShopMapper shopMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    ChatMapper chatMapper;

    @Override
    public Body addchat(String shopid, String userid, String type, String content, String img) {
        Shop shop = shopMapper.selectById(shopid);
        Chat chat = new Chat();
        chat.setAddtime(DateUtils.formatTimeNow());
        if (!StringUtils.isEmpty(img)) {
            chat.setImg(img);
        }
        if (!StringUtils.isEmpty(content)) {
            chat.setContent(content);
        }
        chat.setAdminid(shop.getAdminid());
        chat.setChatid(Hutool.getId());
        chat.setShopid(shopid);
        chat.setType(type);
        chat.setUserid(userid);
        boolean b = this.insert(chat);
        if (b) {
            return Body.BODY_200;
        }
        return Body.BODY_500;
    }

    @Override
    public Body getchatlist(String shopid, String userid) {
        EntityWrapper<Chat> chatEntityWrapper = new EntityWrapper<>();
        chatEntityWrapper.eq("shopid", shopid);
        chatEntityWrapper.eq("userid", userid);
        chatEntityWrapper.orderBy("addtime", true);
        List<Chat> chats = this.selectList(chatEntityWrapper);
        if (chats.size() > 0) {
            for (int i = 0; i < chats.size(); i++) {
                Shop shop = shopMapper.selectById(chats.get(i).getShopid());
                chats.get(i).setShop(shop);
                User user = userMapper.selectById(chats.get(i).getUserid());
                chats.get(i).setUser(user);
            }
        }

        return Body.newInstance(chats);
    }

    @Override
    public Body chatlistByuserid(String userid) {
        List<Shop> list = shopMapper.chatlistByuserid(userid);
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                EntityWrapper<Chat> chatEntityWrapper = new EntityWrapper<>();
                chatEntityWrapper.eq("shopid", list.get(i).getShopid());
                chatEntityWrapper.eq("userid", userid);
                chatEntityWrapper.orderBy("addtime", true);
                List<Chat> chats = this.selectList(chatEntityWrapper);
                if (chats.size() > 0) {
                    list.get(i).setChat(chats.get(0));
                }
            }
        }

        return Body.newInstance(list);
    }

    @Override
    public Body chatlistByshopid(String shopid) {
        List<User> list = userMapper.chatlistByshopid(shopid);
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                EntityWrapper<Chat> chatEntityWrapper = new EntityWrapper<>();
                chatEntityWrapper.eq("shopid", shopid);
                chatEntityWrapper.eq("userid", list.get(i).getUserid());
                chatEntityWrapper.orderBy("addtime", true);
                List<Chat> chats = this.selectList(chatEntityWrapper);
                if (chats.size() > 0) {
                    list.get(i).setChat(chats.get(0));
                }
            }

        }
        return Body.newInstance(list);
    }
}
