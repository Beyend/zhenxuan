package com.zcf.zhenxuan.service;

import com.zcf.zhenxuan.bean.Senior;
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
public interface SeniorService extends IService<Senior> {

    Body addsenior(String userid, String relname, String reltel, String address, String idcard, String business);

    Body getseniorlist(String type);

    Body checksenior(String seniorid, String check);

    Body issenior(String userid);
}
