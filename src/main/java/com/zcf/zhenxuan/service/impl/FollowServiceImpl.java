package com.zcf.zhenxuan.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zcf.zhenxuan.bean.Collections;
import com.zcf.zhenxuan.bean.Follow;
import com.zcf.zhenxuan.mapper.FollowMapper;
import com.zcf.zhenxuan.mapper.ShopMapper;
import com.zcf.zhenxuan.service.FollowService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zcf.zhenxuan.util.DateUtils;
import com.zcf.zhenxuan.util.Hutool;
import com.zcf.zhenxuan.util.json.Body;
import com.zcf.zhenxuan.vo.in.UserParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author kmr123
 * @since 2018-12-18
 */
@Service
public class FollowServiceImpl extends ServiceImpl<FollowMapper, Follow> implements FollowService {

    @Autowired
    FollowMapper followMapper;
    @Autowired
    ShopMapper shopMapper;

    @Override
    public Body addfollow(String shopid, String userid) {
        Follow follow = new Follow();
        follow.setFollowid(Hutool.getId());
        follow.setAddtime(DateUtils.formatTimeNow());
        follow.setShopid(shopid);
        follow.setUserid(userid);
        boolean insert = this.insert(follow);
        if (insert) {
            return Body.BODY_200;
        }

        return Body.BODY_500;
    }

    @Override
    public Body getfollowlist(UserParam param) {
        Page<Follow> page = new Page<Follow>(param.getPageNum(), param.getPageSize());
        EntityWrapper<Follow> eWrapper = new EntityWrapper<Follow>();
        eWrapper.eq("userid", param.getUserid());
        eWrapper.orderBy("addtime", false);
        List<Follow> follows = followMapper.selectList(eWrapper);
        List<Follow> followslist = followMapper.selectPage(page, eWrapper);
        if (followslist.size() > 0) {
            for (int i = 0; i < followslist.size(); i++) {
                followslist.get(i).setShop(shopMapper.selectById(followslist.get(i).getShopid()));
            }

        }
        Map<Object, Object> map = new HashMap<>();
        map.put("total", follows.size());
        map.put("data", followslist);
        return Body.newInstance(map);

    }

    @Override
    public Body canclefollow(String userid, String shopid) {
        EntityWrapper<Follow> followEntityWrapper = new EntityWrapper<>();
        followEntityWrapper.eq("userid", userid);
        followEntityWrapper.eq("shopid", shopid);
        List<Follow> follows = followMapper.selectList(followEntityWrapper);
        boolean b = false;
        if (follows.size() > 0) {
            b = this.deleteById(follows.get(0));
        }
        if (b) {
            return Body.BODY_200;
        }
        return Body.BODY_500;
    }
}
