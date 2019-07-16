package com.zcf.zhenxuan.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.zcf.zhenxuan.bean.Banner;
import com.zcf.zhenxuan.mapper.BannerMapper;
import com.zcf.zhenxuan.mapper.CityMapper;
import com.zcf.zhenxuan.mapper.GoodsMapper;
import com.zcf.zhenxuan.service.BannerService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zcf.zhenxuan.util.Hutool;
import com.zcf.zhenxuan.util.json.Body;
import org.apache.commons.lang3.StringUtils;
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
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements BannerService {
    @Autowired
    BannerMapper bannerMapper;
    @Autowired
    CityMapper cityMapper;
    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public Body addbanner(String cityid, String bannerimg, String goodsid, String level) {
        Banner banner = new Banner();
        banner.setBannerid(Hutool.getId());
        banner.setBannerimg(bannerimg);
        if (!StringUtils.isEmpty(goodsid)) {
            banner.setGoodsid(goodsid);
        }
        banner.setCityid(cityid);
        banner.setLevel(Integer.parseInt(level));
        Integer insert = bannerMapper.insert(banner);
        if (insert > 0) {
            return Body.BODY_200;
        }
        return Body.BODY_500;
    }

    @Override
    public Body changelevel(String bannerid, String level) {
        Banner banner = bannerMapper.selectById(bannerid);
        banner.setLevel(Integer.parseInt(level));
        Integer insert = bannerMapper.insert(banner);
        if (insert > 0) {
            return Body.BODY_200;
        }
        return Body.BODY_500;
    }

    @Override
    public Body getbannerinfo(String bannerid) {
        Banner banner = bannerMapper.selectById(bannerid);
        if (banner != null) {
            return Body.newInstance(banner);
        }
        return Body.BODY_500;
    }

    @Override
    public Body deletebanner(String bannerid) {
        Integer integer = bannerMapper.deleteById(bannerid);
        if (integer > 0) {
            return Body.BODY_200;
        }
        return Body.BODY_500;
    }

    @Override
    public Body getbannerlist(String cityid) {
        EntityWrapper<Banner> bannerEntityWrapper = new EntityWrapper<>();
        if (!StringUtils.isEmpty(cityid)) {
            bannerEntityWrapper.eq("cityid", cityid);
        }
        bannerEntityWrapper.orderBy("level", true);
        List<Banner> banners = bannerMapper.selectList(bannerEntityWrapper);

        return Body.newInstance(banners);
    }
}
