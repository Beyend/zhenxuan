package com.zcf.zhenxuan.service;

import com.zcf.zhenxuan.bean.City;
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
public interface CityService extends IService<City> {

    Body addcity(String cityname);

    Body updatecity(String cityid, String cityname);

    Body getcitys();

    Body deletecity(String cityid);

    Body getcityinfo(String cityid);
}
