package com.zcf.zhenxuan.service;

import com.zcf.zhenxuan.bean.Signlog;
import com.baomidou.mybatisplus.service.IService;
import com.zcf.zhenxuan.util.json.Body;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kmr123
 * @since 2019-01-16
 */
public interface SignlogService extends IService<Signlog> {

    Body getSingBy(String userid);
}
