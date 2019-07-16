package com.zcf.zhenxuan.service.impl;

import com.aliyuncs.exceptions.ClientException;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.github.pagehelper.PageHelper;
import com.zcf.zhenxuan.bean.*;
import com.zcf.zhenxuan.controller.sendsms;
import com.zcf.zhenxuan.controller.util.StringUtil;
import com.zcf.zhenxuan.mapper.*;
import com.zcf.zhenxuan.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zcf.zhenxuan.util.*;
import com.zcf.zhenxuan.util.json.Body;
import com.zcf.zhenxuan.util.result.PageResult;
import com.zcf.zhenxuan.util.result.ResultVo;
import com.zcf.zhenxuan.vo.in.UserParam;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    CityMapper cityMapper;
    @Autowired
    SignlogMapper signlogMapper;
    @Autowired
    AdminMapper adminMapper;
    @Autowired
    SettingMapper settingMapper;
    @Autowired
    LiwulogMapper liwulogMapper;
    @Autowired
    SamecityMapper samecityMapper;

    /**
     * 登录
     *
     * @param phone
     * @param pwd
     * @return
     */
    @Override
    public Body login(String phone, String pwd) {
        EntityWrapper<User> ew = new EntityWrapper<User>();
        ew.eq("phone", phone);
        List<User> list = userMapper.selectList(ew);
        if (list.size() > 0) {
            if (list.get(0).getPwd().equals(MD5Util.MD5EncodeUtf8(pwd))) {

                return Body.newInstance(list.get(0));

            } else {
                return Body.newInstance(888, "密码不正确！");
            }
        } else {
            return Body.newInstance(999, "用户不存在！");
        }


    }

    /**
     * 注册
     *
     * @param phone
     * @param pwd
     * @return
     */
    @Override
    public Body regist(String phone, String pwd) {
        EntityWrapper<User> ew = new EntityWrapper<User>();
        ew.eq("phone", phone);
        List<User> list = userMapper.selectList(ew);
        if (list.size() > 0) {
            return Body.newInstance(777, "该手机号已存在！");
        } else {
            User user = new User();
            user.setUserid(Hutool.getId());
            user.setRelpwd(pwd);
            user.setPwd(MD5Util.MD5EncodeUtf8(pwd));
            user.setAddtime(DateUtils.formatTimeNow());
            user.setPrimary("N");
            user.setSenior("N");
            user.setSign("0");
            user.setPhone(phone);
            user.setCityid("2019h00115Pf1434rp55");
            int count = userMapper.insert(user);
            if (count > 0) {
                return Body.BODY_200;
            }
            return Body.BODY_500;
        }

    }

    /**
     * 修改密码
     *
     * @param userid
     * @param oldPwd
     * @param newPwd
     * @return
     */
    @Override
    public Body updatePwd(String userid, String oldPwd, String newPwd) {
        User user = userMapper.selectById(userid);
        if (user.getPwd().equals(MD5Util.MD5EncodeUtf8(oldPwd))) {
            user.setPwd(MD5Util.MD5EncodeUtf8(newPwd));
            user.setRelpwd(newPwd);
            int count = userMapper.updateById(user);
            if (count > 0) {
                return Body.BODY_200;
            }
            return Body.BODY_500;
        } else {
            return Body.newInstance(777, "旧密码不正确！");


        }
    }

    /**
     * 忘记密码
     *
     * @param phone
     * @param newPwd
     * @return
     */
    @Override
    public Body forgetPwd(String phone, String newPwd) {
        EntityWrapper<User> ew = new EntityWrapper<User>();
        ew.eq("phone", phone);
        List<User> list = userMapper.selectList(ew);
        if (list.size() > 0) {
            list.get(0).setRelpwd(newPwd);
            list.get(0).setPwd(MD5Util.MD5EncodeUtf8(newPwd));
            int count = userMapper.updateById(list.get(0));
            if (count > 0) {
                return Body.BODY_200;
            }
            return Body.BODY_500;
        } else {
            return Body.newInstance(777, "手机号不存在!");
        }


    }

    /**
     * 发送验证码
     *
     * @param userid
     * @param phone
     * @return
     */
    @Override
    public Body sendCode(String userid, String phone) {
        if (!StringUtils.isEmpty(userid)) {
            User user = userMapper.selectById(userid);
            if (user.getPhone().equals(phone)) {
                String num = IDUtils.Random();
                sendsms.sendSms(phone, num);
                return Body.newInstance(num);
            } else {
                return Body.newInstance(777, "手机号不正确！");
            }
        } else {
            String num = IDUtils.Random();

            sendsms.sendSms(phone, num);
            return Body.newInstance(num);

        }

    }

    /**
     * 修改个人信息
     *
     * @param userid
     * @param username
     * @param userimg
     * @return
     */
    @Override
    public Body updateUser(String userid, String username, String userimg) {
        User user = userMapper.selectById(userid);
        if (user != null) {
            if (!StringUtils.isEmpty(username)) {
                user.setUsername(username);
            }
            if (!StringUtils.isEmpty(userimg)) {
                user.setUserimg(userimg);
            }
            int count = userMapper.updateById(user);
            if (count > 0) {
                return Body.BODY_200;
            }
        }

        return Body.BODY_500;
    }

    /**
     * 获取实时地址
     *
     * @param userid
     * @param city
     * @return
     */
    @Override
    public Body changeaddress(String userid, String city) {
        User user = userMapper.selectById(userid);
        EntityWrapper<City> ew = new EntityWrapper<>();
        ew.like("cityname", city);
        List<City> list = cityMapper.selectList(ew);
        if (list.size() > 0) {
            user.setCityid(list.get(0).getCityid());
            int count = userMapper.updateById(user);
            if (count > 0) {
                return Body.newInstance(user);
            }
        }
        return Body.BODY_500;
    }

    /**
     * 签到
     * TODO 判断中没中奖
     *
     * @param userid
     * @return
     */
    @Override
    public Body sign(String userid) {
        EntityWrapper<Signlog> signlogEntityWrapper = new EntityWrapper<>();
        signlogEntityWrapper.eq("userid", userid);
        signlogEntityWrapper.like("signtime", DateUtils.formatDateNow());
        List<Signlog> signlogs = signlogMapper.selectList(signlogEntityWrapper);
        if (signlogs.size() > 0) {
            return Body.newInstance(888, "今日已经签到过，无法再次签到");
        }
        User user = userMapper.selectById(userid);
        user.setSign(Integer.parseInt(user.getSign()) + 10 + "");
        int count = userMapper.updateById(user);
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("sign", "0");
        if (count > 0) {

            //判断中没中奖
            EntityWrapper<Signlog> signlogEntityWrapper1 = new EntityWrapper<>();
            signlogEntityWrapper1.eq("userid", userid);
            signlogEntityWrapper1.like("signtime", DateUtils.formatDateNow().substring(0, 7));
            signlogEntityWrapper1.orderBy("signtime", false);
            List<Signlog> signloglist = signlogMapper.selectList(signlogEntityWrapper1);
            System.out.println(signloglist.size());
            if (signloglist.size() > 0) {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                Date firstDate = null;
                Date secondDate = null;
                try {
                    firstDate = df.parse(signloglist.get(0).getSigntime().substring(0, 10));
                    secondDate = df.parse(DateUtils.formatDateNow());
                } catch (Exception e) {
                    // 日期型字符串格式错误
                    System.out.println("日期型字符串格式错误");
                }
                int nDay = (int) ((secondDate.getTime() - firstDate.getTime()) / (24 * 60 * 60 * 1000));
                System.out.println(nDay);
                if (nDay == 1) {
                    user.setSignday(Integer.parseInt(user.getSignday()) + 1 + "");
                    userMapper.updateById(user);
                    //累计七天签到
                    if (Integer.parseInt(user.getSignday()) == 7) {
                        EntityWrapper<Liwulog> liwulogEntityWrapper = new EntityWrapper<>();
                        liwulogEntityWrapper.eq("setid", "1");
                        liwulogEntityWrapper.eq("zhongjiangmongth", DateUtils.formatDateNow().substring(0, 7));
                        List<Liwulog> liwulogs = liwulogMapper.selectList(liwulogEntityWrapper);
                        Settings setting = settingMapper.selectById("1");
                        if (liwulogs.size() < Integer.parseInt(setting.getNum())) {
                            //插入获取礼物记录
                            Liwulog liwulog = new Liwulog();
                            liwulog.setUserid(userid);
                            liwulog.setSetid("1");
                            liwulog.setZhongjiangmongth(DateUtils.formatDateNow().substring(0, 7));
                            liwulog.setLiwulogid(Hutool.getId());
                            liwulogMapper.insert(liwulog);
                            objectObjectHashMap.put("data", "恭喜你，累计7天获得" + setting.getLiwu());
                            objectObjectHashMap.put("sign", liwulog.getLiwulogid());
                        } else {
                            objectObjectHashMap.put("data", "很遗憾，累计7天获得礼物数量已达上限");
                            objectObjectHashMap.put("sign", "0");
                        }
                        //累计14天签到
                    } else if (Integer.parseInt(user.getSignday()) == 14) {

                        EntityWrapper<Liwulog> liwulogEntityWrapper = new EntityWrapper<>();
                        liwulogEntityWrapper.eq("setid", "2");
                        liwulogEntityWrapper.eq("zhongjiangmongth", DateUtils.formatDateNow().substring(0, 7));
                        List<Liwulog> liwulogs = liwulogMapper.selectList(liwulogEntityWrapper);

                        Settings setting = settingMapper.selectById("2");
                        if (liwulogs.size() < Integer.parseInt(setting.getNum())) {
                            Liwulog liwulog = new Liwulog();
                            liwulog.setUserid(userid);
                            liwulog.setSetid("2");
                            liwulog.setZhongjiangmongth(DateUtils.formatDateNow().substring(0, 7));
                            liwulog.setLiwulogid(Hutool.getId());
                            liwulogMapper.insert(liwulog);
                            objectObjectHashMap.put("data", "恭喜你，累计14天获得" + setting.getLiwu());
                            objectObjectHashMap.put("sign", liwulog.getLiwulogid());
                        } else {
                            objectObjectHashMap.put("data", "很遗憾，累计14天获得礼物数量已达上限");
                            objectObjectHashMap.put("sign", "0");
                        }


                    } else if (Integer.parseInt(user.getSignday()) == 21) {

                        EntityWrapper<Liwulog> liwulogEntityWrapper = new EntityWrapper<>();
                        liwulogEntityWrapper.eq("setid", "3");
                        liwulogEntityWrapper.eq("zhongjiangmongth", DateUtils.formatDateNow().substring(0, 7));
                        List<Liwulog> liwulogs = liwulogMapper.selectList(liwulogEntityWrapper);

                        Settings setting = settingMapper.selectById("3");
                        if (liwulogs.size() < Integer.parseInt(setting.getNum())) {
                            Liwulog liwulog = new Liwulog();
                            liwulog.setUserid(userid);
                            liwulog.setSetid("3");
                            liwulog.setZhongjiangmongth(DateUtils.formatDateNow().substring(0, 7));
                            liwulog.setLiwulogid(Hutool.getId());
                            liwulogMapper.insert(liwulog);
                            objectObjectHashMap.put("data", "恭喜你，累计21天获得" + setting.getLiwu());
                            objectObjectHashMap.put("sign", liwulog.getLiwulogid());
                        } else {
                            objectObjectHashMap.put("data", "很遗憾，累计21天获得礼物数量已达上限");
                            objectObjectHashMap.put("sign", "0");
                        }


                    } else if (Integer.parseInt(user.getSignday()) == 28) {

                        EntityWrapper<Liwulog> liwulogEntityWrapper = new EntityWrapper<>();
                        liwulogEntityWrapper.eq("setid", "4");
                        liwulogEntityWrapper.eq("zhongjiangmongth", DateUtils.formatDateNow().substring(0, 7));
                        List<Liwulog> liwulogs = liwulogMapper.selectList(liwulogEntityWrapper);
                        Settings setting = settingMapper.selectById("4");
                        if (liwulogs.size() < Integer.parseInt(setting.getNum())) {
                            Liwulog liwulog = new Liwulog();
                            liwulog.setUserid(userid);
                            liwulog.setSetid("4");
                            liwulog.setZhongjiangmongth(DateUtils.formatDateNow().substring(0, 7));
                            liwulog.setLiwulogid(Hutool.getId());
                            liwulogMapper.insert(liwulog);
                            objectObjectHashMap.put("data", "恭喜你，累计28天获得" + setting.getLiwu());
                            objectObjectHashMap.put("sign", liwulog.getLiwulogid());
                        } else {
                            objectObjectHashMap.put("data", "很遗憾，累计28天获得礼物数量已达上限");
                            objectObjectHashMap.put("sign", "0");
                        }
                    }


                } else {
                    //间隔 没有累计签到
                    user.setSignday("1");
                    userMapper.updateById(user);
                }


            } else {
                //新的月份
                user.setSignday("1");
                userMapper.updateById(user);
            }


            //插入签到记录
            Signlog signlog = new Signlog();
            signlog.setSignlogid(Hutool.getId());
            signlog.setUserid(userid);
            signlog.setSigntime(DateUtils.formatTimeNow());
            signlogMapper.insert(signlog);

            objectObjectHashMap.put("day", user.getSignday());
            return Body.newInstance(objectObjectHashMap);
        }
        return Body.BODY_500;
    }

    @Override
    public Body changephone(String userid, String oldphone, String newphone) {
        User user = userMapper.selectById(userid);
        if (oldphone.equals(user.getPhone())) {
            user.setPhone(newphone);
            Integer integer = userMapper.updateById(user);
            if (integer > 0) {
                return Body.BODY_200;
            }
            return Body.BODY_500;
        } else {
            return Body.newInstance(777, "手机号不正确");
        }
    }

    @Override
    public Body getuserlistbycity(UserParam param) {

        Page<User> page = new Page<User>(param.getPageNum(), param.getPageSize());
        EntityWrapper<User> eWrapper = new EntityWrapper<User>();
        if (!StringUtils.isEmpty(param.getCityid())) {
            eWrapper.eq("cityid", param.getCityid());
        }
        eWrapper.orderBy("addtime", false);
        List<User> users = userMapper.selectList(eWrapper);
        List<User> userList = userMapper.selectPage(page, eWrapper);
        if (userList.size() > 0) {
            for (int i = 0; i < userList.size(); i++) {
                if (!StringUtils.isEmpty(userList.get(i).getCityid())) {
                    userList.get(i).setCityname(cityMapper.selectById(userList.get(i).getCityid()).getCityname());
                }

            }
        }
        Map<Object, Object> map = new HashMap<>();
        map.put("total", users.size());
        map.put("data", userList);
        return Body.newInstance(map);
    }

    @Override
    public Body getuserinfo(String userid) {
        User user = userMapper.selectById(userid);
        return Body.newInstance(user);

    }

    @Override
    public Body shangchuan(MultipartFile[] file) {
        try {
            String url = UploadImgUtils.uploadFiles(file);
            return Body.newInstance(url);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Body getuserlistbyshop(UserParam param) {
        // 没有设置，默认首页10条数据显示
        if (param.getPageNum() != null && param.getPageSize() != null) {
            PageHelper.startPage(param.getPageNum(), param.getPageSize());
        }
        // 自动分页
        PageResult result = PageResult.result(userMapper.getuserlistbyshop(param));
        // 返回的vo
        ResultVo resultVo = new ResultVo();
        // 返回总条数
        List<User> list = userMapper.getuserlistbyshop(param);
        resultVo.setTotal((long) list.size());
        // 返回结果集合
        resultVo.setList(result.getRows());
        return Body.newInstance(resultVo);
    }

    @Override
    public Body deleteUser(String userid) {
        boolean b = this.deleteById(userid);
        if (b) {
            EntityWrapper<Samecity> samecityEntityWrapper = new EntityWrapper<>();
            samecityEntityWrapper.eq("userid", userid);
            samecityMapper.delete(samecityEntityWrapper);
            return Body.BODY_200;
        }

        return Body.BODY_500;

    }

    public static void main(String[] args) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date firstDate = null;
        Date secondDate = null;
        try {
            firstDate = df.parse("2019-03-07");
            secondDate = df.parse("2019-03-25");
        } catch (Exception e) {
            // 日期型字符串格式错误
            System.out.println("日期型字符串格式错误");
        }
        int nDay = (int) ((secondDate.getTime() - firstDate.getTime()) / (24 * 60 * 60 * 1000));
        System.out.println(nDay);
    }
}
