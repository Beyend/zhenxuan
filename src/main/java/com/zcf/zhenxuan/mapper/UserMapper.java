package com.zcf.zhenxuan.mapper;

import com.zcf.zhenxuan.bean.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zcf.zhenxuan.vo.in.UserParam;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author kmr123
 * @since 2018-12-18
 */
public interface UserMapper extends BaseMapper<User> {

    List<User> chatlistByshopid(String shopid);

    List<User> getuserlistbyshop(UserParam param);
}
