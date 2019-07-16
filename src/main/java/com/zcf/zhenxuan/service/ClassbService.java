package com.zcf.zhenxuan.service;

import com.zcf.zhenxuan.bean.Classb;
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
public interface ClassbService extends IService<Classb> {

    Body addclassb(String classaid, String classbname, String classbimg);

    Body updateclassb(String classbid, String classbname, String classbimg);

    Body deleteclassb(String classbid);

    Body getclassblist(String classaid);

    Body getclassbinfo(String classbid);

    Body getAgencyClassList(String classaid);
}
