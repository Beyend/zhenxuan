package com.zcf.zhenxuan.service;

import com.baomidou.mybatisplus.service.IService;
import com.zcf.zhenxuan.bean.Collections;
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
public interface CollectionService extends IService<Collections> {

    Body addcollection(String goodsid, String userid);

    Body canclecollectiono(String userid,String  goodsid);

    Body getcollections(UserParam param);
}
