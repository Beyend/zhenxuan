package com.zcf.zhenxuan.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zcf.zhenxuan.bean.User;
import com.zcf.zhenxuan.bean.Watch;
import com.zcf.zhenxuan.mapper.GoodsMapper;
import com.zcf.zhenxuan.mapper.SamecityMapper;
import com.zcf.zhenxuan.mapper.WatchMapper;
import com.zcf.zhenxuan.service.WatchService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zcf.zhenxuan.util.json.Body;
import com.zcf.zhenxuan.vo.in.UserParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author kmr123
 * @since 2018-12-18
 */
@Service
public class WatchServiceImpl extends ServiceImpl<WatchMapper, Watch> implements WatchService {
    @Autowired
    WatchMapper watchMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    SamecityMapper samecityMapper;

    @Override
    public Body getwatchlist(UserParam param) {
        Page<Watch> page = new Page<Watch>(param.getPageNum(), param.getPageSize());
        EntityWrapper<Watch> eWrapper = new EntityWrapper<Watch>();
        eWrapper.eq("userid", param.getUserid());
        eWrapper.eq("type", "2");
        eWrapper.orderBy("addtime", false);
        List<Watch> watch = watchMapper.selectList(eWrapper);
        List<Watch> watchList = watchMapper.selectPage(page, eWrapper);
        if (watchList.size() > 0) {
            for (int i = 0; i < watchList.size(); i++) {
                watchList.get(i).setSamecity(samecityMapper.selectById(watchList.get(i).getSeeid()));
            }
        }
        Map<Object, Object> map = new HashMap<>();
        map.put("total", watch.size());
        map.put("data", watchList);
        return Body.newInstance(map);
    }

    @Override
    public Body getwatchlistgoods(UserParam param) {
        Page<Watch> page = new Page<Watch>(param.getPageNum(), param.getPageSize());
        EntityWrapper<Watch> eWrapper = new EntityWrapper<Watch>();
        eWrapper.eq("userid", param.getUserid());
        eWrapper.eq("type", "1");
        eWrapper.orderBy("addtime", false);
        List<Watch> watch = watchMapper.selectList(eWrapper);
        List<Watch> watchList = watchMapper.selectPage(page, eWrapper);
        if (watchList.size() > 0) {
            for (int i = 0; i < watchList.size(); i++) {
                watchList.get(i).setGoods(goodsMapper.selectById(watchList.get(i).getSeeid()));
            }
        }
        Map<Object, Object> map = new HashMap<>();
        map.put("total", watch.size());
        map.put("data", watchList);
        return Body.newInstance(map);
    }
}
