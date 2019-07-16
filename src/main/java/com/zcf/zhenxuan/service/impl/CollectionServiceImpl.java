package com.zcf.zhenxuan.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zcf.zhenxuan.bean.Collections;
import com.zcf.zhenxuan.bean.Log;
import com.zcf.zhenxuan.bean.Shop;
import com.zcf.zhenxuan.mapper.CollectionMapper;
import com.zcf.zhenxuan.mapper.GoodsMapper;
import com.zcf.zhenxuan.mapper.LogMapper;
import com.zcf.zhenxuan.service.CollectionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zcf.zhenxuan.util.DateUtils;
import com.zcf.zhenxuan.util.Hutool;
import com.zcf.zhenxuan.util.json.Body;
import com.zcf.zhenxuan.vo.in.UserParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
public class CollectionServiceImpl extends ServiceImpl<CollectionMapper, Collections> implements CollectionService {

    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    CollectionMapper collectionMapper;


    @Override
    public Body addcollection(String goodsid, String userid) {
        Collections collections = new Collections();
        collections.setCollectionid(Hutool.getId());
        collections.setAddtime(DateUtils.formatTimeNow());
        collections.setUserid(userid);
        collections.setGoodsid(goodsid);
        Integer insert = collectionMapper.insert(collections);
        if (insert > 0) {
            return Body.BODY_200;
        }

        return Body.BODY_500;
    }

    @Override
    public Body canclecollectiono(String userid, String goodsid) {
        EntityWrapper<Collections> collectionsEntityWrapper = new EntityWrapper<>();
        collectionsEntityWrapper.eq("goodsid", goodsid);
        if (!StringUtils.isEmpty(userid)) {
            collectionsEntityWrapper.eq("userid", userid);
        }
        List<Collections> collections = collectionMapper.selectList(collectionsEntityWrapper);
        Integer integer = 0;
        if (collections.size() > 0) {
            integer = collectionMapper.deleteById(collections.get(0));
        }
        if (integer > 0) {
            return Body.BODY_200;
        }
        return Body.BODY_500;
    }

    @Override
    public Body getcollections(UserParam param) {

        Page<Collections> page = new Page<Collections>(param.getPageNum(), param.getPageSize());
        EntityWrapper<Collections> eWrapper = new EntityWrapper<Collections>();
        eWrapper.eq("userid", param.getUserid());
        eWrapper.orderBy("addtime", false);
        List<Collections> collections = collectionMapper.selectList(eWrapper);
        List<Collections> collectionslist = collectionMapper.selectPage(page, eWrapper);
        if (collectionslist.size() > 0) {
            for (int i = 0; i < collectionslist.size(); i++) {
                collectionslist.get(i).setGoods(goodsMapper.selectById(collectionslist.get(i).getGoodsid()));
            }

        }
        Map<Object, Object> map = new HashMap<>();
        map.put("total", collections.size());
        map.put("data", collectionslist);
        return Body.newInstance(map);
    }
}
