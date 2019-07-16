package com.zcf.zhenxuan.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.zcf.zhenxuan.bean.City;
import com.zcf.zhenxuan.mapper.CityMapper;
import com.zcf.zhenxuan.service.CityService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zcf.zhenxuan.util.DateUtils;
import com.zcf.zhenxuan.util.Hutool;
import com.zcf.zhenxuan.util.json.Body;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author kmr123
 * @since 2018-12-18
 */
@Service
public class CityServiceImpl extends ServiceImpl<CityMapper, City> implements CityService {
    @Autowired
    CityMapper cityMapper;

    @Override
    public Body addcity(String cityname) {

        City city = new City();
        city.setCityid(Hutool.getId());
        city.setCityname(cityname);
        city.setAddtime(DateUtils.formatTimeNow());
        Integer insert = cityMapper.insert(city);
        if (insert > 0) {
            return Body.BODY_200;
        }
        return Body.BODY_500;
    }

    @Override
    public Body updatecity(String cityid, String cityname) {
        City city = cityMapper.selectById(cityid);
        city.setCityname(cityname);
        Integer integer = cityMapper.updateById(city);
        if (integer > 0) {
            return Body.BODY_200;
        }
        return Body.BODY_500;
    }

    @Override
    public Body getcitys() {
        EntityWrapper<City> cityEntityWrapper = new EntityWrapper<>();
        cityEntityWrapper.orderBy("cityname", true);
        List<City> cities = cityMapper.selectList(cityEntityWrapper);
        return Body.newInstance(cities);
    }

    @Override
    public Body deletecity(String cityid) {
        Integer integer = cityMapper.deleteById(cityid);
        if (integer > 0) {
            return Body.BODY_200;
        }
        return Body.BODY_500;
    }

    @Override
    public Body getcityinfo(String cityid) {
        City city = cityMapper.selectById(cityid);
        return Body.newInstance(city);
    }
}
