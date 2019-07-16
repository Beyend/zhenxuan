package com.zcf.zhenxuan.service;

import com.zcf.zhenxuan.bean.Goods;
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
public interface GoodsService extends IService<Goods> {

    Body addgoods(String goodsname, String goodslogo, String classbid, String shopid, String introduce, String num, String price);

    Body getgoodsinfo(String goodsid, String userid);

    Body getgoodslist(UserParam param);

    Body downgoods(String goodsid);

    Body upgoods(String goodsid);

    Body setmiaosha(String goodsid, String spike);

    Body settop(String goodsid, String toplevel);

    Body gettoplist(String cityid);

    Body getmiaoshalist(UserParam param);

    Body canclemiaosha(String goodsid);

    Body settuijian(String goodsid);

    Body cancletuijian(String goodsid);

    Body gettuijianlist(UserParam param);

    Body getgoodsListbycity(String cityid);

    Body updategoods(String goodsid, String goodsname, String goodslogo, String introduce, String num, String price);
}
