package com.zcf.zhenxuan.service;

import com.zcf.zhenxuan.bean.Primary;
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
public interface PrimaryService extends IService<Primary> {

    Body addprimary(String userid, String relname, String reltel, String address);

    Body checkprimary(String primaryid, String check);

    Body getprimary(String type);

    Body isprimary(String userid);
}
