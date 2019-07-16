package com.zcf.zhenxuan.service;

import com.zcf.zhenxuan.bean.Browse;
import com.baomidou.mybatisplus.service.IService;
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
public interface BrowseService extends IService<Browse> {

    Body addbrowse(String content, String img, String userid, String username, String lianxi);

    Body getbrowse(UserParam param);
}
