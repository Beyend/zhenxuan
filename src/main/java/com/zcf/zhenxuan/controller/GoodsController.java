package com.zcf.zhenxuan.controller;


import com.zcf.zhenxuan.service.GoodsService;
import com.zcf.zhenxuan.util.json.Body;
import com.zcf.zhenxuan.vo.in.UserParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author kmr123
 * @since 2018-12-18
 */
@Controller
@RequestMapping("/goods")
@ResponseBody
@CrossOrigin
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    /**
     * 添加商品
     *
     * @param goodsname
     * @param goodslogo
     * @param classbid
     * @param shopid
     * @param introduce
     * @param num       最多个购买数量
     * @param price
     * @return
     */
    @PostMapping("/addgoods")
    public Body addgoods(String goodsname, String goodslogo, String classbid, String shopid, String introduce, String num, String price) {
        return goodsService.addgoods(goodsname, goodslogo, classbid, shopid, introduce, num, price);
    }

    /**
     * 修改商品信息
     * @param goodsid
     * @param goodsname
     * @param goodslogo
     * @param introduce
     * @param num
     * @param price
     * @return
     */
    @PostMapping("/updategoods")
    public  Body  updategoods(String goodsid,String goodsname, String goodslogo,String introduce, String num, String price){
        return goodsService.updategoods( goodsid, goodsname,  goodslogo, introduce,  num,  price);
    }
    /**
     * 查看商品详情
     *
     * @param goodsid
     * @param userid
     * @return
     */
    @PostMapping("/getgoodsinfo")
    public Body getgoodsinfo(String goodsid, String userid) {
        return goodsService.getgoodsinfo(goodsid, userid);
    }

    /**
     * 查看商品列表
     *
     * @param param
     * @return
     */
    @PostMapping("/getgoodslist")
    public Body getgoodslist(UserParam param) {
        return goodsService.getgoodslist(param);
    }

    /**
     * 下架商品
     *
     * @param goodsid
     * @return
     */
    @PostMapping("/downgoods")
    public Body downgoods(String goodsid) {
        return goodsService.downgoods(goodsid);
    }

    /**
     * 上架商品
     *
     * @param goodsid
     * @return
     */
    @PostMapping("/upgoods")
    public Body upgoods(String goodsid) {
        return goodsService.upgoods(goodsid);
    }

    /**
     * 设置成秒杀商品
     *
     * @param goodsid
     * @param spike
     * @return
     */
    @PostMapping("/setmiaosha")
    public Body setmiaosha(String goodsid, String spike) {
        return goodsService.setmiaosha(goodsid, spike);
    }

    /**
     * 取消秒杀商品
     *
     * @param goodsid
     * @return
     */
    @PostMapping("/canclemiaosha")
    public Body canclemiaosha(String goodsid) {
        return goodsService.canclemiaosha(goodsid);
    }

    /**
     * 秒杀商品置顶
     *
     * @param goodsid
     * @param toplevel
     * @return
     */
    @PostMapping("/settop")
    public Body settop(String goodsid, String toplevel) {
        return goodsService.settop(goodsid, toplevel);
    }

    /**
     * 所有秒杀商品
     *
     * @return
     */
    @PostMapping("/getmiaoshalist")
    public Body getmiaoshalist(UserParam param) {
        return goodsService.getmiaoshalist(param);
    }

    /**
     * 查看置顶秒杀商品
     *
     * @return
     */
    @PostMapping("/gettoplist")
    public Body gettoplist(String cityid) {
        return goodsService.gettoplist(cityid);
    }

    /**
     * 设置为推荐商品
     *
     * @param goodsid
     * @return
     */
    @PostMapping("/settuijian")
    public Body settuijian(String goodsid) {
        return goodsService.settuijian(goodsid);
    }
    /**
     * 取消推荐商品
     *
     * @param goodsid
     * @return
     */
    @PostMapping("/cancletuijian")
    public Body cancletuijian(String goodsid) {
        return goodsService.cancletuijian(goodsid);
    }

    /**
     * 查看推荐商品
     *
     * @return
     */
    @PostMapping("/gettuijianlist")
    public Body gettuijianlist(UserParam param) {
        return goodsService.gettuijianlist(param);
    }


    @PostMapping("/getgoodsListbycity")
    public Body  getgoodsListbycity(String cityid){
            return goodsService.getgoodsListbycity(cityid);
    }
}

