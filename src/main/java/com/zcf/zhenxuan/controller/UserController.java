package com.zcf.zhenxuan.controller;


import com.zcf.zhenxuan.bean.Address;
import com.zcf.zhenxuan.bean.Order;
import com.zcf.zhenxuan.mapper.AddressMapper;
import com.zcf.zhenxuan.mapper.OrderMapper;
import com.zcf.zhenxuan.service.UserService;
import com.zcf.zhenxuan.util.json.Body;
import com.zcf.zhenxuan.vo.in.UserParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author kmr123
 * @since 2018-12-18
 */
@Controller
@RequestMapping("/user")
@ResponseBody
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    AddressMapper addressMapper;


    /**
     * 注册
     *
     * @param phone
     * @param pwd
     * @return
     */
    @PostMapping("/regist")
    public Body regist(String phone, String pwd) {
        return userService.regist(phone, pwd);
    }

    /**
     * 用户登录
     *
     * @param phone
     * @param pwd
     * @return
     */
    @PostMapping("/login")
    public Body login(String phone, String pwd) {
        return userService.login(phone, pwd);
    }

    /**
     * 修改密码
     *
     * @param userid
     * @param oldPwd
     * @param newPwd
     * @return
     */
    @PostMapping("/updatePwd")
    public Body updatePwd(String userid, String oldPwd, String newPwd) {
        return userService.updatePwd(userid, oldPwd, newPwd);
    }

    /**
     * 忘记密码
     *
     * @param phone
     * @param newPwd
     * @return
     */
    @PostMapping("/forgetPwd")
    public Body forgetPwd(String phone, String newPwd) {
        return userService.forgetPwd(phone, newPwd);
    }

    /**
     * 发送验证码
     *
     * @param userid
     * @param phone
     * @return
     */
    @PostMapping("/sendCode")
    public Body sendCode(String userid, String phone) {
        return userService.sendCode(userid, phone);
    }

    /**
     * 修改个人资料
     *
     * @param userid
     * @param username
     * @param userimg
     * @return
     */
    @PostMapping("/updateUser")
    public Body updateUser(String userid, String username, String userimg) {
        return userService.updateUser(userid, username, userimg);
    }

    /**
     * 获取当前用户地址
     *
     * @param userid
     * @param city
     * @return
     */
    @PostMapping("/changeaddress")
    public Body changeaddress(String userid, String city) {
        return userService.changeaddress(userid, city);
    }

    /**
     * 签到（每次赠送10积分）
     *
     * @param userid
     * @return
     */
    @PostMapping("/sign")
    public Body sign(String userid) {
        return userService.sign(userid);
    }

    /**
     * 修改手机号
     *
     * @param userid
     * @param oldphone
     * @param newphone
     * @return
     */
    @PostMapping("/changephone")
    public Body changephone(String userid, String oldphone, String newphone) {
        return userService.changephone(userid, oldphone, newphone);
    }

    /**
     * 查看某个地区的所有用户
     *
     * @param param
     * @return
     */
    @PostMapping("/getuserlistbycity")
    public Body getuserlistbycity(UserParam param) {
        return userService.getuserlistbycity(param);
    }

    /**
     * 查看个人信息
     *
     * @param userid
     * @return
     */
    @PostMapping("/getuserinfo")
    public Body getuserinfo(String userid) {
        return userService.getuserinfo(userid);
    }

    /**
     * 上传文件
     *
     * @return
     */
    @PostMapping("/shangchuan")
    public Body shangchuan(MultipartFile[] file) {
        return userService.shangchuan(file);
    }

    /**
     * 查看商家得用户
     *
     * @return
     */
    @PostMapping("/getuserlistbyshop")
    public Body getuserlistbyshop(UserParam param) {
        return userService.getuserlistbyshop(param);

    }

    /**
     * 删除用户
     *
     * @param userid
     * @return
     */
    @PostMapping("/deleteUser")
    public Body deleteUser(String userid) {
        return userService.deleteUser(userid);
    }


}

