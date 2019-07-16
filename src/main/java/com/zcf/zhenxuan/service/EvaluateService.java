package com.zcf.zhenxuan.service;

import com.zcf.zhenxuan.bean.Evaluate;
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
public interface EvaluateService extends IService<Evaluate> {

    Body addevaluate(String orderid, String userid, String score, String content, String img);

    Body getevaluatlist(String goodsid);
}
