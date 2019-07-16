package com.zcf.zhenxuan.service;

import com.zcf.zhenxuan.bean.Classa;
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
public interface ClassaService extends IService<Classa> {

    Body addclassa(String classaname, String attributeid);

    Body updateclassa(String classaid, String classaname);

    Body getclassalist(String attributeid);

    Body deleteclassa(String classaid);

    Body getclassainfo(String classaid);
}
