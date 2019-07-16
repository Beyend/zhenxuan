package com.zcf.zhenxuan.service;

import com.zcf.zhenxuan.bean.Follow;
import com.baomidou.mybatisplus.service.IService;
import com.zcf.zhenxuan.util.json.Body;
import com.zcf.zhenxuan.vo.in.UserParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kmr123
 * @since 2018-12-18
 */
public interface FollowService extends IService<Follow> {

    Body addfollow(String shopid, String userid);

    Body getfollowlist(UserParam param);

    Body canclefollow(String userid,String  shopid);
}
