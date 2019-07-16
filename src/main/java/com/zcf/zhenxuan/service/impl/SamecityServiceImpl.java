package com.zcf.zhenxuan.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zcf.zhenxuan.bean.*;
import com.zcf.zhenxuan.mapper.*;
import com.zcf.zhenxuan.service.SamecityService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zcf.zhenxuan.util.DateUtils;
import com.zcf.zhenxuan.util.Hutool;
import com.zcf.zhenxuan.util.json.Body;
import com.zcf.zhenxuan.vo.in.UserParam;
import org.apache.commons.lang3.StringUtils;
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
 * @since 2018-12-19
 */
@Service
public class SamecityServiceImpl extends ServiceImpl<SamecityMapper, Samecity> implements SamecityService {

    @Autowired
    SeniorMapper seniorMapper;
    @Autowired
    PrimaryMapper primaryMapper;
    @Autowired
    WatchMapper watchMapper;
    @Autowired
    SamecityMapper samecityMapper;
    @Autowired
    UserMapper userMapper;

    @Override
    public Body addsamecity(String userid, String title, String content, String sameimg, String label, String price, String metre, String type, String uname, String tell, String startpath, String endpath, String sex, String age, String address, String daiyu, String xueli, String area, String louceng, String huxing, String cheku, String chucang, String zhuangxiu, String chaoxiang, String fangyuanjieshao, String paytype, String zhengjian) {


        EntityWrapper<Samecity> samecityEntityWrapper = new EntityWrapper<>();
        samecityEntityWrapper.eq("userid", userid);
        samecityEntityWrapper.like("addtime", DateUtils.formatTimeNow().substring(0, 10));
        List<Samecity> samecities = this.selectList(samecityEntityWrapper);
        if (samecities.size() < 3) {
            Samecity samecity = new Samecity();
            samecity.setSamecityid(Hutool.getId());
            samecity.setAddtime(DateUtils.formatTimeNow());
            samecity.setType(type);
            samecity.setUname(uname);
            samecity.setTell(tell);
            samecity.setTitle(title);
            samecity.setContent(content);

            if (!StringUtils.isEmpty(daiyu)) {
                samecity.setDaiyu(daiyu);
            }
            if (!StringUtils.isEmpty(xueli)) {
                samecity.setXueli(xueli);
            }
            if (!StringUtils.isEmpty(area)) {
                samecity.setArea(area);
            }
            if (!StringUtils.isEmpty(louceng)) {
                samecity.setLouceng(louceng);
            }
            if (!StringUtils.isEmpty(huxing)) {
                samecity.setHuxing(huxing);
            }
            if (!StringUtils.isEmpty(cheku)) {
                samecity.setCheku(cheku);
            }
            if (!StringUtils.isEmpty(chucang)) {
                samecity.setChucang(chucang);
            }
            if (!StringUtils.isEmpty(zhuangxiu)) {
                samecity.setZhuangxiu(zhuangxiu);
            }
            if (!StringUtils.isEmpty(chaoxiang)) {
                samecity.setChaoxiang(chaoxiang);
            }
            if (!StringUtils.isEmpty(fangyuanjieshao)) {
                samecity.setFangyuanjieshao(fangyuanjieshao);
            }
            if (!StringUtils.isEmpty(paytype)) {
                samecity.setPaytype(paytype);
            }
            if (!StringUtils.isEmpty(zhengjian)) {
                samecity.setZhengjian(zhengjian);
            }


            if (!StringUtils.isEmpty(address)) {
                samecity.setAddress(address);
            }
            if (!StringUtils.isEmpty(age)) {
                samecity.setAge(age);
            }
            if (!StringUtils.isEmpty(endpath)) {
                samecity.setEndpath(endpath);
            }
            if (!StringUtils.isEmpty(label)) {
                samecity.setLabel(label);
            }
            if (!StringUtils.isEmpty(metre)) {
                samecity.setMetre(metre);
            }
            if (!StringUtils.isEmpty(price)) {
                samecity.setPrice(price);
            }
            if (!StringUtils.isEmpty(sameimg)) {
                samecity.setSameimg(sameimg);
            }
            if (!StringUtils.isEmpty(sex)) {
                samecity.setSex(sex);
            }
            if (!StringUtils.isEmpty(startpath)) {
                samecity.setStartpath(startpath);
            }
            samecity.setUserid(userid);
            samecity.setTuijian("N");
            boolean b = this.insert(samecity);
            if (b) {
                return Body.BODY_200;
            }
            return Body.BODY_500;
        } else if (samecities.size() == 3) {
            if ("6".equals(type)) {
                EntityWrapper<Senior> seniorEntityWrapper = new EntityWrapper<>();
                seniorEntityWrapper.eq("userid", userid);
                List<Senior> seniors = seniorMapper.selectList(seniorEntityWrapper);
                if (seniors.size() > 0) {
                    if ("1".equals(seniors.get(0).getStatus())) {
                        Samecity samecity = new Samecity();
                        samecity.setSamecityid(Hutool.getId());
                        samecity.setAddtime(DateUtils.formatTimeNow());
                        samecity.setType(type);
                        samecity.setUname(uname);
                        samecity.setTell(tell);
                        samecity.setTitle(title);
                        samecity.setContent(content);
                        if (!StringUtils.isEmpty(daiyu)) {
                            samecity.setDaiyu(daiyu);
                        }
                        if (!StringUtils.isEmpty(xueli)) {
                            samecity.setXueli(xueli);
                        }
                        if (!StringUtils.isEmpty(area)) {
                            samecity.setArea(area);
                        }
                        if (!StringUtils.isEmpty(louceng)) {
                            samecity.setLouceng(louceng);
                        }
                        if (!StringUtils.isEmpty(huxing)) {
                            samecity.setHuxing(huxing);
                        }
                        if (!StringUtils.isEmpty(cheku)) {
                            samecity.setCheku(cheku);
                        }
                        if (!StringUtils.isEmpty(chucang)) {
                            samecity.setChucang(chucang);
                        }
                        if (!StringUtils.isEmpty(zhuangxiu)) {
                            samecity.setZhuangxiu(zhuangxiu);
                        }
                        if (!StringUtils.isEmpty(chaoxiang)) {
                            samecity.setChaoxiang(chaoxiang);
                        }
                        if (!StringUtils.isEmpty(fangyuanjieshao)) {
                            samecity.setFangyuanjieshao(fangyuanjieshao);
                        }
                        if (!StringUtils.isEmpty(paytype)) {
                            samecity.setPaytype(paytype);
                        }
                        if (!StringUtils.isEmpty(zhengjian)) {
                            samecity.setZhengjian(zhengjian);
                        }

                        if (!StringUtils.isEmpty(address)) {
                            samecity.setAddress(address);
                        }
                        if (!StringUtils.isEmpty(age)) {
                            samecity.setAge(age);
                        }
                        if (!StringUtils.isEmpty(endpath)) {
                            samecity.setEndpath(endpath);
                        }
                        if (!StringUtils.isEmpty(label)) {
                            samecity.setLabel(label);
                        }
                        if (!StringUtils.isEmpty(metre)) {
                            samecity.setMetre(metre);
                        }
                        if (!StringUtils.isEmpty(price)) {
                            samecity.setPrice(price);
                        }
                        if (!StringUtils.isEmpty(sameimg)) {
                            samecity.setSameimg(sameimg);
                        }
                        if (!StringUtils.isEmpty(sex)) {
                            samecity.setSex(sex);
                        }
                        if (!StringUtils.isEmpty(startpath)) {
                            samecity.setStartpath(startpath);
                        }
                        samecity.setUserid(userid);
                        samecity.setTuijian("N");
                        boolean b = this.insert(samecity);
                        if (b) {
                            return Body.BODY_200;
                        }
                        return Body.BODY_500;
                    } else {
                        return Body.newInstance(777, "您还未通过认证审核，请耐心等待或者重新申请认证");
                    }
                } else {
                    return Body.newInstance(555, "您本月发布信息条数达到上线，请先去认证审核再发信息");
                }

            } else {
                EntityWrapper<Primary> primaryEntityWrapper = new EntityWrapper<>();
                primaryEntityWrapper.eq("userid", userid);
                List<Primary> primaries = primaryMapper.selectList(primaryEntityWrapper);
                if (primaries.size() > 0) {
                    if ("1".equals(primaries.get(0).getStatus())) {
                        Samecity samecity = new Samecity();
                        samecity.setSamecityid(Hutool.getId());
                        samecity.setAddtime(DateUtils.formatTimeNow());
                        samecity.setType(type);
                        samecity.setUname(uname);
                        samecity.setTell(tell);
                        samecity.setTitle(title);
                        samecity.setContent(content);
                        if (!StringUtils.isEmpty(daiyu)) {
                            samecity.setDaiyu(daiyu);
                        }
                        if (!StringUtils.isEmpty(xueli)) {
                            samecity.setXueli(xueli);
                        }
                        if (!StringUtils.isEmpty(area)) {
                            samecity.setArea(area);
                        }
                        if (!StringUtils.isEmpty(louceng)) {
                            samecity.setLouceng(louceng);
                        }
                        if (!StringUtils.isEmpty(huxing)) {
                            samecity.setHuxing(huxing);
                        }
                        if (!StringUtils.isEmpty(cheku)) {
                            samecity.setCheku(cheku);
                        }
                        if (!StringUtils.isEmpty(chucang)) {
                            samecity.setChucang(chucang);
                        }
                        if (!StringUtils.isEmpty(zhuangxiu)) {
                            samecity.setZhuangxiu(zhuangxiu);
                        }
                        if (!StringUtils.isEmpty(chaoxiang)) {
                            samecity.setChaoxiang(chaoxiang);
                        }
                        if (!StringUtils.isEmpty(fangyuanjieshao)) {
                            samecity.setFangyuanjieshao(fangyuanjieshao);
                        }
                        if (!StringUtils.isEmpty(paytype)) {
                            samecity.setPaytype(paytype);
                        }
                        if (!StringUtils.isEmpty(zhengjian)) {
                            samecity.setZhengjian(zhengjian);
                        }

                        if (!StringUtils.isEmpty(address)) {
                            samecity.setAddress(address);
                        }
                        if (!StringUtils.isEmpty(age)) {
                            samecity.setAge(age);
                        }
                        if (!StringUtils.isEmpty(endpath)) {
                            samecity.setEndpath(endpath);
                        }
                        if (!StringUtils.isEmpty(label)) {
                            samecity.setLabel(label);
                        }
                        if (!StringUtils.isEmpty(metre)) {
                            samecity.setMetre(metre);
                        }
                        if (!StringUtils.isEmpty(price)) {
                            samecity.setPrice(price);
                        }
                        if (!StringUtils.isEmpty(sameimg)) {
                            samecity.setSameimg(sameimg);
                        }
                        if (!StringUtils.isEmpty(sex)) {
                            samecity.setSex(sex);
                        }
                        if (!StringUtils.isEmpty(startpath)) {
                            samecity.setStartpath(startpath);
                        }
                        samecity.setUserid(userid);
                        samecity.setTuijian("N");
                        boolean b = this.insert(samecity);
                        if (b) {
                            return Body.BODY_200;
                        }
                        return Body.BODY_500;
                    } else {
                        return Body.newInstance(777, "您还未通过初级认证审核，请耐心等待或者重新申请认证");
                    }
                } else {
                    return Body.newInstance(555, "您本月发布信息条数达到上线，请先去认证审核再发信息");
                }

            }
        } else if (samecities.size() > 3 && samecities.size() < 30) {
            Samecity samecity = new Samecity();
            samecity.setSamecityid(Hutool.getId());
            samecity.setAddtime(DateUtils.formatTimeNow());
            samecity.setType(type);
            samecity.setUname(uname);
            samecity.setTell(tell);
            samecity.setTitle(title);
            samecity.setContent(content);
            if (!StringUtils.isEmpty(daiyu)) {
                samecity.setDaiyu(daiyu);
            }
            if (!StringUtils.isEmpty(xueli)) {
                samecity.setXueli(xueli);
            }
            if (!StringUtils.isEmpty(area)) {
                samecity.setArea(area);
            }
            if (!StringUtils.isEmpty(louceng)) {
                samecity.setLouceng(louceng);
            }
            if (!StringUtils.isEmpty(huxing)) {
                samecity.setHuxing(huxing);
            }
            if (!StringUtils.isEmpty(cheku)) {
                samecity.setCheku(cheku);
            }
            if (!StringUtils.isEmpty(chucang)) {
                samecity.setChucang(chucang);
            }
            if (!StringUtils.isEmpty(zhuangxiu)) {
                samecity.setZhuangxiu(zhuangxiu);
            }
            if (!StringUtils.isEmpty(chaoxiang)) {
                samecity.setChaoxiang(chaoxiang);
            }
            if (!StringUtils.isEmpty(fangyuanjieshao)) {
                samecity.setFangyuanjieshao(fangyuanjieshao);
            }
            if (!StringUtils.isEmpty(paytype)) {
                samecity.setPaytype(paytype);
            }
            if (!StringUtils.isEmpty(zhengjian)) {
                samecity.setZhengjian(zhengjian);
            }

            if (!StringUtils.isEmpty(address)) {
                samecity.setAddress(address);
            }
            if (!StringUtils.isEmpty(age)) {
                samecity.setAge(age);
            }
            if (!StringUtils.isEmpty(endpath)) {
                samecity.setEndpath(endpath);
            }
            if (!StringUtils.isEmpty(label)) {
                samecity.setLabel(label);
            }
            if (!StringUtils.isEmpty(metre)) {
                samecity.setMetre(metre);
            }
            if (!StringUtils.isEmpty(price)) {
                samecity.setPrice(price);
            }
            if (!StringUtils.isEmpty(sameimg)) {
                samecity.setSameimg(sameimg);
            }
            if (!StringUtils.isEmpty(sex)) {
                samecity.setSex(sex);
            }
            if (!StringUtils.isEmpty(startpath)) {
                samecity.setStartpath(startpath);
            }
            samecity.setUserid(userid);
            samecity.setTuijian("N");
            boolean b = this.insert(samecity);
            if (b) {
                return Body.BODY_200;
            }
            return Body.BODY_500;
        } else if (samecities.size() == 30) {
            return Body.newInstance(888, "您本月发布的信息已经达到上限，请下个月再进行消息发布！");
        }

        return Body.BODY_500;
    }

    @Override
    public Body getsamecityinfo(String samecityid, String userid) {

        Samecity samecity = this.selectById(samecityid);
        samecity.setUser(userMapper.selectById(samecity.getUserid()));
        Watch watch = new Watch();
        watch.setWatchid(Hutool.getId());
        watch.setSeeid(samecityid);
        watch.setType("2");
        watch.setUserid(userid);
        watch.setAddtime(DateUtils.formatTimeNow());
        watchMapper.insert(watch);
        return Body.newInstance(samecity);
    }

    @Override
    public Body getsamecitylist(UserParam param) {
        Page<Samecity> page = new Page<Samecity>(param.getPageNum(), param.getPageSize());
        EntityWrapper<Samecity> eWrapper = new EntityWrapper<Samecity>();
        if (!StringUtils.isEmpty(param.getType())) {
            eWrapper.eq("type", param.getType());
        }
        if (!StringUtils.isEmpty(param.getSearch())) {
            eWrapper.like("title", param.getSearch());
        }
        if (!StringUtils.isEmpty(param.getUserid())) {
            eWrapper.eq("userid", param.getUserid());
        }
        eWrapper.orderBy("addtime", false);
        List<Samecity> samecitys = samecityMapper.selectList(eWrapper);
        List<Samecity> samecitylist = samecityMapper.selectPage(page, eWrapper);
        if (samecitylist.size() > 0) {
            for (int i = 0; i < samecitylist.size(); i++) {
                samecitylist.get(i).setUser(userMapper.selectById(samecitylist.get(i).getUserid()));
            }
        }
        Map<Object, Object> map = new HashMap<>();
        map.put("total", samecitys.size());
        map.put("data", samecitylist);
        return Body.newInstance(map);
    }

    @Override
    public Body tuijiansamecity(String samecityid) {

        Samecity samecity = this.selectById(samecityid);
        samecity.setTuijian("Y");
        boolean b = this.updateById(samecity);
        if (b) {
            return Body.BODY_200;
        }
        return Body.BODY_500;
    }

    @Override
    public Body canclesamecity(String samecityid) {

        Samecity samecity = this.selectById(samecityid);
        samecity.setTuijian("N");
        boolean b = this.updateById(samecity);
        if (b) {
            return Body.BODY_200;
        }
        return Body.BODY_500;
    }

    @Override
    public Body gettuijianlist(UserParam param) {
        Page<Samecity> page = new Page<Samecity>(param.getPageNum(), param.getPageSize());
        EntityWrapper<Samecity> eWrapper = new EntityWrapper<Samecity>();
        eWrapper.eq("tuijian", "Y");
        if (!StringUtils.isEmpty(param.getSearch())) {
            eWrapper.like("title", param.getSearch());
        }
        eWrapper.orderBy("addtime", false);
        List<Samecity> samecitys = samecityMapper.selectList(eWrapper);
        List<Samecity> samecitylist = samecityMapper.selectPage(page, eWrapper);
        Map<Object, Object> map = new HashMap<>();
        map.put("total", samecitys.size());
        map.put("data", samecitylist);
        return Body.newInstance(map);
    }

    @Override
    public Body deletesamecity(String samecityid) {
        boolean b = this.deleteById(samecityid);
        if (b) {
            return Body.BODY_200;
        }
        return Body.BODY_500;
    }
}
