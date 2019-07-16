package com.zcf.zhenxuan.service;

import com.zcf.zhenxuan.bean.Samecity;
import com.baomidou.mybatisplus.service.IService;
import com.zcf.zhenxuan.util.json.Body;
import com.zcf.zhenxuan.vo.in.UserParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kmr123
 * @since 2018-12-19
 */
public interface SamecityService extends IService<Samecity> {

    Body addsamecity(String userid, String title, String content, String sameimg, String label, String price, String metre, String type, String uname, String tell, String startpath, String endpath, String sex, String age, String address, String daiyu, String xueli, String area, String louceng, String huxing, String cheku, String chucang, String zhuangxiu, String chaoxiang, String fangyuanjieshao, String paytype, String zhengjian);

    Body getsamecityinfo(String samecityid, String userid);

    Body getsamecitylist(UserParam param);

    Body tuijiansamecity(String samecityid);

    Body canclesamecity(String samecityid);

    Body gettuijianlist(UserParam param);

    Body deletesamecity(String samecityid);
}
