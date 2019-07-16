package com.zcf.zhenxuan.service;

import com.baomidou.mybatisplus.service.IService;
import com.zcf.zhenxuan.bean.Liwulog;
import com.zcf.zhenxuan.util.json.Body;
import com.zcf.zhenxuan.vo.in.UserParam;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author kmr123
 * @since 2019-01-16
 */
public interface LiwulogService extends IService<Liwulog> {

    Body getLiwuloglist(UserParam param);

    Body zhongjiang(String liwulogid, String lianxi, String tel);
}
