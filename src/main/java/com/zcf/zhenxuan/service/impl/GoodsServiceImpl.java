package com.zcf.zhenxuan.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zcf.zhenxuan.bean.Collections;
import com.zcf.zhenxuan.bean.Goods;
import com.zcf.zhenxuan.bean.Shop;
import com.zcf.zhenxuan.bean.Watch;
import com.zcf.zhenxuan.mapper.*;
import com.zcf.zhenxuan.service.GoodsService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zcf.zhenxuan.util.DateUtils;
import com.zcf.zhenxuan.util.Hutool;
import com.zcf.zhenxuan.util.json.Body;
import com.zcf.zhenxuan.util.json.Meta;
import com.zcf.zhenxuan.vo.in.UserParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {
    @Autowired
    WatchMapper watchMapper;
    @Autowired
    CollectionMapper collectionMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    ShopMapper shopMapper;
    @Autowired
    ClassbMapper classbMapper;


    @Override
    public Body addgoods(String goodsname, String goodslogo, String classbid, String shopid, String introduce, String num, String price) {
        Goods goods = new Goods();
        goods.setGoodsid(Hutool.getId());
        goods.setClassbid(classbid);
        goods.setGoodslogo(goodslogo);
        goods.setIntroduce(introduce);
        goods.setGoodsname(goodsname);
        goods.setIsdelete("N");
        goods.setPrice(price);
        goods.setTuijian("0");
        goods.setNum(Integer.parseInt(num));
        goods.setShopid(shopid);
        goods.setType("1");
        goods.setAddtime(DateUtils.formatTimeNow());
        boolean insert = this.insert(goods);
        if (insert) {
            return Body.BODY_200;
        }
        return Body.BODY_500;
    }

    @Override
    public Body getgoodsinfo(String goodsid, String userid) {
        Goods goods = this.selectById(goodsid);

        goods.setClassb(classbMapper.selectById(goods.getClassbid()));
        goods.setShop(shopMapper.selectById(goods.getShopid()));
        EntityWrapper<Collections> collectionsEntityWrapper = new EntityWrapper<>();
        collectionsEntityWrapper.eq("goodsid", goodsid);
        if (!StringUtils.isEmpty(userid)) {
            collectionsEntityWrapper.eq("userid", userid);
        }
        List<Collections> collections = collectionMapper.selectList(collectionsEntityWrapper);
        if (collections.size() > 0) {
            goods.setIscollection("Y");
        } else {
            goods.setIscollection("N");
        }
        if ("Y".equals(goods.getIsdelete())) {
            return Body.newInstance(Meta.CODE_400, goods);
        } else {
            Watch watch = new Watch();
            watch.setWatchid(Hutool.getId());
            watch.setSeeid(goodsid);
            watch.setType("1");
            watch.setUserid(userid);
            watch.setAddtime(DateUtils.formatTimeNow());
            watchMapper.insert(watch);
            return Body.newInstance(goods);
        }


    }

    @Override
    public Body getgoodslist(UserParam param) {
        Page<Goods> page = new Page<Goods>(param.getPageNum(), param.getPageSize());
        EntityWrapper<Goods> ew = new EntityWrapper<Goods>();
        if (!StringUtils.isEmpty(param.getClassbid())) {
            ew.eq("classbid", param.getClassbid());
        }
        if (!StringUtils.isEmpty(param.getShopid())) {
            ew.eq("shopid", param.getShopid());
        }
        if (!StringUtils.isEmpty(param.getGoodsname())) {
            ew.like("goodsname", param.getGoodsname());
        }
        ew.eq("isdelete", "N");
        ew.orderBy("addtime", false);
        List<Goods> goodss = goodsMapper.selectList(ew);
        List<Goods> goodslist = goodsMapper.selectPage(page, ew);
        if(StringUtils.isNotBlank(param.getCityid()) && !CollectionUtils.isEmpty(goodslist)){
            for(int i  = 0 ; i < goodslist.size() ; i++){
                Shop shop = shopMapper.selectById(goodslist.get(i).getShopid());
                if(!shop.getCityid().equals(param.getCityid())){
                    goodslist.remove(goodslist.get(i));
                    i--;
                }
            }
        }
        if (goodslist.size() > 0) {
            for (int i = 0; i < goodslist.size(); i++) {
                if (!StringUtils.isEmpty(goodslist.get(i).getClassbid())) {
                    if (classbMapper.selectById(goodslist.get(i).getClassbid()) != null) {
                        if (!StringUtils.isEmpty(classbMapper.selectById(goodslist.get(i).getClassbid()).getClassbname())) {
                            goodslist.get(i).setClassbname(classbMapper.selectById(goodslist.get(i).getClassbid()).getClassbname());
                        }

                    }

                }
            }
        }
        Map<Object, Object> map = new HashMap<>();
        map.put("total", goodss.size());
        map.put("data", goodslist);
        return Body.newInstance(map);

    }

    @Override
    public Body downgoods(String goodsid) {
        Goods goods = this.selectById(goodsid);
        goods.setIsdelete("Y");
        boolean b = this.updateById(goods);
        if (b) {
            return Body.BODY_200;
        }
        return Body.BODY_500;
    }

    @Override
    public Body upgoods(String goodsid) {
        Goods goods = this.selectById(goodsid);
        goods.setIsdelete("N");
        boolean b = this.updateById(goods);
        if (b) {
            return Body.BODY_200;
        }
        return Body.BODY_500;
    }

    @Override
    public Body setmiaosha(String goodsid, String spike) {
        Goods goods = this.selectById(goodsid);
        goods.setSpike(spike);
        goods.setType("2");
        goods.setTop("N");
        boolean b = this.updateById(goods);
        if (b) {
            return Body.BODY_200;
        }
        return Body.BODY_500;
    }

    @Override
    public Body settop(String goodsid, String toplevel) {
        Goods goods = this.selectById(goodsid);
        goods.setTop("Y");
        goods.setToplevel(toplevel);
        boolean b = this.updateById(goods);
        if (b) {
            return Body.BODY_200;
        }
        return Body.BODY_500;
    }

    @Override
    public Body gettoplist(String cityid) {
        EntityWrapper<Goods> goodsEntityWrapper = new EntityWrapper<>();
        goodsEntityWrapper.eq("type", "2");
        goodsEntityWrapper.eq("top", "Y");
        goodsEntityWrapper.eq("isdelete", "N");
        goodsEntityWrapper.orderBy("toplevel", true);
        List<Goods> goods = this.selectList(goodsEntityWrapper);
        if(StringUtils.isNotBlank(cityid) && !CollectionUtils.isEmpty(goods)){
            for(int i  = 0 ; i < goods.size() ; i++){
                Shop shop = shopMapper.selectById(goods.get(i).getShopid());
                if(!shop.getCityid().equals(cityid)){
                    goods.remove(goods.get(i));
                    i--;
                }
            }
        }
        return Body.newInstance(goods);
    }

    @Override
    public Body getmiaoshalist(UserParam param) {
        Page<Goods> page = new Page<Goods>(param.getPageNum(), param.getPageSize());
        EntityWrapper<Goods> eWrapper = new EntityWrapper<Goods>();
        eWrapper.eq("type", "2");
        eWrapper.eq("isdelete", "N");
        eWrapper.orderBy("addtime", false);
        List<Goods> goodss = goodsMapper.selectList(eWrapper);
        List<Goods> goodslist = goodsMapper.selectPage(page, eWrapper);
        if(StringUtils.isNotBlank(param.getCityid()) && !CollectionUtils.isEmpty(goodslist)){
            for(int i  = 0 ; i < goodslist.size() ; i++){
                Shop shop = shopMapper.selectById(goodslist.get(i).getShopid());
                if(!shop.getCityid().equals(param.getCityid())){
                    goodslist.remove(goodslist.get(i));
                    i--;
                }
            }
        }
        Map<Object, Object> map = new HashMap<>();
        map.put("total", goodss.size());
        map.put("data", goodslist);
        return Body.newInstance(map);
    }

    @Override
    public Body canclemiaosha(String goodsid) {
        Goods goods = this.selectById(goodsid);
        goods.setTop("N");
        goods.setToplevel("");
        goods.setType("1");
        boolean b = this.updateById(goods);
        if (b) {
            return Body.BODY_200;
        }
        return Body.BODY_500;
    }

    @Override
    public Body settuijian(String goodsid) {
        Goods goods = this.selectById(goodsid);
        goods.setTuijian("1");
        boolean b = this.updateById(goods);
        if (b) {
            return Body.BODY_200;
        }
        return Body.BODY_500;
    }

    @Override
    public Body cancletuijian(String goodsid) {
        Goods goods = this.selectById(goodsid);
        goods.setTuijian("0");
        boolean b = this.updateById(goods);
        if (b) {
            return Body.BODY_200;
        }
        return Body.BODY_500;

    }

    @Override
    public Body gettuijianlist(UserParam param) {
        EntityWrapper<Goods> goodsEntityWrapper = new EntityWrapper<>();
        String goodsname = param.getGoodsname();
        if (!StringUtils.isEmpty(goodsname)) {
            goodsEntityWrapper.like("goodsname", goodsname);
        }

        goodsEntityWrapper.eq("isdelete", "N");
        goodsEntityWrapper.eq("tuijian", "1");
        goodsEntityWrapper.orderBy("paynum", false);
        List<Goods> goods = this.selectList(goodsEntityWrapper);
        if(StringUtils.isNotBlank(param.getCityid()) && !CollectionUtils.isEmpty(goods)){
            for(int i  = 0 ; i < goods.size() ; i++){
                Shop shop = shopMapper.selectById(goods.get(i).getShopid());
                if(!shop.getCityid().equals(param.getCityid())){
                    goods.remove(goods.get(i));
                    i--;
                }
            }
        }
        return Body.newInstance(goods);
    }

    @Override
    public Body getgoodsListbycity(String cityid) {

        List<Goods> list = goodsMapper.getgoodsListbycity(cityid);
        return Body.newInstance(list);
    }

    @Override
    public Body updategoods(String goodsid, String goodsname, String goodslogo, String introduce, String num, String price) {
        Goods goods = this.selectById(goodsid);
        goods.setGoodsname(goodsname);
        goods.setNum(Integer.parseInt(num));
        goods.setPrice(price);
        goods.setGoodslogo(goodslogo);
        goods.setIntroduce(introduce);
        boolean b = this.updateById(goods);
        if (b) {
            return Body.BODY_200;
        }
        return Body.BODY_500;
    }
}
