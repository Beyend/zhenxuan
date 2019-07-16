package com.zcf.zhenxuan.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zcf.zhenxuan.bean.*;
import com.zcf.zhenxuan.mapper.*;
import com.zcf.zhenxuan.service.ShopService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zcf.zhenxuan.util.DateUtils;
import com.zcf.zhenxuan.util.Hutool;
import com.zcf.zhenxuan.util.json.Body;
import com.zcf.zhenxuan.util.json.Meta;
import com.zcf.zhenxuan.vo.in.UserParam;
import com.zcf.zhenxuan.vo.out.OrderTotalVo;
import org.apache.commons.lang3.StringUtils;
import org.omg.IOP.ENCODING_CDR_ENCAPS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements ShopService {

    @Autowired
    ShopMapper shopMapper;
    @Autowired
    AdminMapper adminMapper;
    @Autowired
    FollowMapper followMapper;
    @Autowired
    CityMapper cityMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    BannerMapper bannerMapper;
    @Autowired
    OrderMapper orderMapper;


    @Override
    public Body addshop(String shopname, String username, String tel, String address, String account, String pwd, String cityid, String introduce) {
        EntityWrapper<Admin> shopEntityWrapper = new EntityWrapper<>();
        shopEntityWrapper.eq("account", account);
        List<Admin> admins = adminMapper.selectList(shopEntityWrapper);
        if (admins.size() > 0) {
            return Body.newInstance(255, "账号已存在，请重新输入");
        } else {
            Admin admin = new Admin();
            admin.setAdminid(Hutool.getId());
            admin.setAccount(account);
            admin.setPwd(pwd);
            admin.setCityid(cityid);
            admin.setType("2");
            admin.setAddtime(DateUtils.formatTimeNow());
            adminMapper.insert(admin);
            Shop shop = new Shop();
            shop.setIsdelete("N");
            if (!StringUtils.isEmpty(address)) {
                shop.setAddress(address);
            }
            shop.setAdminid(admin.getAdminid());
            shop.setAddtime(DateUtils.formatTimeNow());
            shop.setUsername(username);
            shop.setShopname(shopname);
            if (!StringUtils.isEmpty(introduce)) {
                shop.setIntroduce(introduce);
            }
            shop.setTel(tel);
            shop.setShopid(Hutool.getId());
            shop.setCityid(cityid);
            boolean b = this.insert(shop);
            if (b) {
                return Body.BODY_200;
            }
            return Body.BODY_500;
        }
    }

    @Override
    public Body getshopbyadmin(String adminid) {
        EntityWrapper<Shop> shopEntityWrapper = new EntityWrapper<>();
        shopEntityWrapper.eq("adminid", adminid);
        List<Shop> shops = this.selectList(shopEntityWrapper);

        return Body.newInstance(shops.get(0));
    }

    @Override
    public Body updateshop(String shopid, String shoplogo, String introduce, String shopimg, String shopname) {
        Shop shop = this.selectById(shopid);

        if (!StringUtils.isEmpty((shopname))) {
            shop.setShopname(shopname);
        }
        if (!StringUtils.isEmpty((shoplogo))) {
            shop.setShoplogo(shoplogo);
        }
        if (!StringUtils.isEmpty((introduce))) {
            shop.setIntroduce(introduce);
        }
        if (!StringUtils.isEmpty((shopimg))) {
            shop.setShopimg(shopimg);
        }
        boolean b = this.updateById(shop);
        if (b) {
            return Body.BODY_200;
        }
        return Body.BODY_500;
    }

    @Override
    public Body getadminshop(String adminid) {
        EntityWrapper<Shop> shopEntityWrapper = new EntityWrapper<>();
        shopEntityWrapper.eq("adminid", adminid);
        List<Shop> shops = this.selectList(shopEntityWrapper);
        if (shops.size() > 0) {
            return Body.newInstance(shops.get(0));
        }
        return Body.newInstance(null);
    }

    @Override
    public Body shanchuShop(String shopid) {
        Shop shop = this.selectById(shopid);
        boolean b = this.deleteById(shopid);
        if (b) {
            EntityWrapper<Goods> goodsEntityWrapper = new EntityWrapper<>();
            goodsEntityWrapper.eq("shopid", shopid);
            goodsMapper.delete(goodsEntityWrapper);
            List<Goods> goods = goodsMapper.selectList(goodsEntityWrapper);
            if (goods.size() > 0) {
                for (int i = 0; i < goods.size(); i++) {
                    EntityWrapper<Banner> bannerEntityWrapper = new EntityWrapper<>();
                    bannerEntityWrapper.eq("goodsid", goods.get(i).getGoodsid());
                    bannerMapper.delete(bannerEntityWrapper);
                }
            }
            EntityWrapper<Order> orderEntityWrapper = new EntityWrapper<>();
            orderEntityWrapper.eq("shopid", shopid);
            orderMapper.delete(orderEntityWrapper);
            adminMapper.deleteById(shop.getAdminid());
            return Body.BODY_200;
        }
        return Body.BODY_500;

    }

    @Override
    public Body getshops(UserParam param) {
        Page<Shop> page = new Page<Shop>(param.getPageNum(), param.getPageSize());
        EntityWrapper<Shop> eWrapper = new EntityWrapper<Shop>();
        if (!StringUtils.isEmpty(param.getCityid())) {
            eWrapper.eq("cityid", param.getCityid());
        }
        if (!StringUtils.isEmpty(param.getShopisdelete())) {
            eWrapper.eq("isdelete", "N");
        }
        if (!StringUtils.isEmpty(param.getAttributeid())) {
            eWrapper.eq("attributeid", param.getAttributeid());
        }
        if (!StringUtils.isEmpty(param.getShopname())) {
            eWrapper.like("shopname", param.getShopname());
        }
        List<Shop> shops = shopMapper.selectList(eWrapper);
        List<Shop> shops1 = shopMapper.selectPage(page, eWrapper);
        if (shops1.size() > 0) {
            for (int i = 0; i < shops1.size(); i++) {
                if (!StringUtils.isEmpty(shops1.get(i).getCityid())) {
                    shops1.get(i).setCityname(cityMapper.selectById(shops1.get(i).getCityid()).getCityname());
                }
                OrderTotalVo total = orderMapper.getShopTotal(shops1.get(i).getShopid(),param.getBegDate(),param.getEndDate());
                if(total != null){//销售数量和金额
                    shops1.get(i).setTotalnum(total.getTotalnum());
                    shops1.get(i).setTotalmoney(total.getTotalmoney());
                }else{
                    shops1.get(i).setTotalnum(0);
                    shops1.get(i).setTotalmoney("0");
                }
                OrderTotalVo refund = orderMapper.getShopRefund(shops1.get(i).getShopid());
                if(refund != null){//退款金额
                    shops1.get(i).setRefund(refund.getRefund());
                }else {
                    shops1.get(i).setRefund("0");
                }
            }
        }
        Map<Object, Object> map = new HashMap<>();
        map.put("total", shops.size());
        map.put("data", shops1);
        return Body.newInstance(map);
    }

    @Override
    public Body getshopinfo(String shopid, String userid) {
        Shop shop = shopMapper.selectById(shopid);
        EntityWrapper<Follow> followEntityWrapper = new EntityWrapper<>();
        followEntityWrapper.eq("userid", userid);
        followEntityWrapper.eq("shopid", shopid);
        List<Follow> follows = followMapper.selectList(followEntityWrapper);
        if (follows.size() > 0) {
            shop.setIsfollow("Y");

        } else {
            shop.setIsfollow("N");
        }
        if ("N".equals(shop.getIsdelete())) {
            return Body.newInstance(shop);
        } else {
            return Body.newInstance(Meta.CODE_400, shop);
        }

    }

    @Override
    public Body deleteshop(String shopid) {
        Shop shop = shopMapper.selectById(shopid);
        shop.setIsdelete("Y");
        Integer integer = shopMapper.updateById(shop);
        if (integer > 0) {
            EntityWrapper<Goods> goodsEntityWrapper = new EntityWrapper<>();
            goodsEntityWrapper.eq("shopid", shopid);
            List<Goods> goods = goodsMapper.selectList(goodsEntityWrapper);
            if (goods.size() > 0) {
                for (int i = 0; i < goods.size(); i++) {
                    goods.get(i).setIsdelete("Y");
                    goodsMapper.updateById(goods.get(i));
                }
            }
            return Body.BODY_200;
        }
        return Body.BODY_500;
    }

    @Override
    public Body upshop(String shopid) {
        Shop shop = shopMapper.selectById(shopid);
        shop.setIsdelete("N");
        Integer integer = shopMapper.updateById(shop);
        if (integer > 0) {
            return Body.BODY_200;
        }
        return Body.BODY_500;
    }


}
