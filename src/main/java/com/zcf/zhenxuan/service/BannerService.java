package com.zcf.zhenxuan.service;

import com.zcf.zhenxuan.bean.Banner;
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
public interface BannerService extends IService<Banner> {

    Body addbanner(String cityid, String bannerimg, String goodsid, String level);

    Body changelevel(String bannerid, String level);

    Body getbannerinfo(String bannerid);

    Body deletebanner(String bannerid);

    Body getbannerlist(String cityid);
}
