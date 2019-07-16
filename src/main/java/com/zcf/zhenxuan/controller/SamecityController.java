package com.zcf.zhenxuan.controller;


import com.zcf.zhenxuan.service.SamecityService;
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
 * @since 2018-12-19
 */
@Controller
@RequestMapping("/samecity")
@ResponseBody
@CrossOrigin
public class SamecityController {
    @Autowired
    SamecityService samecityServicel;

    /**
     * 发布同城消息
     *
     * @param userid
     * @param title
     * @param content
     * @param sameimg
     * @param price
     * @param metre
     * @param type
     * @param uname
     * @param tell
     * @param startpath
     * @param endpath
     * @param sex
     * @param age
     * @param address
     * @param daiyu
     * @param xueli
     * @param area
     * @param louceng
     * @param huxing
     * @param cheku
     * @param chucang
     * @param zhuangxiu
     * @param chaoxiang
     * @param fangyuanjieshao
     * @param paytype
     * @param zhengjian
     * @return
     */
    @PostMapping("/addsamecity")
    public Body addsamecity(String userid, String title, String content, String sameimg, String label, String price, String metre, String type, String uname, String tell, String startpath, String endpath, String sex, String age, String address,
                            String daiyu,
                            String xueli,
                            String area,
                            String louceng,
                            String huxing,
                            String cheku,
                            String chucang,
                            String zhuangxiu,
                            String chaoxiang,
                            String fangyuanjieshao,
                            String paytype,
                            String zhengjian) {
        return samecityServicel.addsamecity(userid, title, content, sameimg, label, price, metre, type, uname, tell, startpath, endpath, sex, age, address, daiyu,
                xueli,
                area,
                louceng,
                huxing,
                cheku,
                chucang,
                zhuangxiu,
                chaoxiang,
                fangyuanjieshao,
                paytype,
                zhengjian);
    }

    /**
     * 查看同城信息详情
     *
     * @param samecityid
     * @param userid
     * @return
     */
    @PostMapping("/getsamecityinfo")
    public Body getsamecityinfo(String samecityid, String userid) {
        return samecityServicel.getsamecityinfo(samecityid, userid);
    }

    /**
     * 查看同城分类列表
     *
     * @param param
     * @return
     */
    @PostMapping("/getsamecitylist")
    public Body getsamecitylist(UserParam param) {
        return samecityServicel.getsamecitylist(param);
    }

    /**
     * 设置为推荐消息
     *
     * @param samecityid
     * @return
     */
    @PostMapping("/tuijiansamecity")
    public Body tuijiansamecity(String samecityid) {
        return samecityServicel.tuijiansamecity(samecityid);
    }

    /**
     * 取消推荐
     *
     * @param samecityid
     * @return
     */
    @PostMapping("/canclesamecity")
    public Body canclesamecity(String samecityid) {
        return samecityServicel.canclesamecity(samecityid);
    }

    /**
     * 查看推荐列表
     *
     * @param param
     * @return
     */
    @PostMapping("/gettuijianlist")
    public Body gettuijianlist(UserParam param) {
        return samecityServicel.gettuijianlist(param);
    }

    /**
     * 删除个人同城
     *
     * @param samecityid
     * @return
     */
    @PostMapping("/deletesamecity")
    public Body deletesamecity(String samecityid) {
        return samecityServicel.deletesamecity(samecityid);
    }

}

