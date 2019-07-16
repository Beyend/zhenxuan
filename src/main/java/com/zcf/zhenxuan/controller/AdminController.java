package com.zcf.zhenxuan.controller;


import com.zcf.zhenxuan.service.AdminService;
import com.zcf.zhenxuan.util.json.Body;
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
@RequestMapping("/admin")
@ResponseBody
@CrossOrigin
public class AdminController {
    @Autowired
    AdminService adminService;

    /**
     * 登录
     *
     * @param account
     * @param pwd
     * @return
     */
    @PostMapping("/loginadmin")
    public Body loginadmin(String account, String pwd) {
        return adminService.loginadmin(account, pwd);
    }


    /**
     * 添加代理
     *
     * @param account
     * @param pwd
     * @param cityid
     * @return
     */
    @PostMapping("/adddaili")
    public Body adddaili(String account, String pwd, String cityid) {
        return adminService.adddaili(account, pwd, cityid);
    }

    /**
     * 添加商家
     *
     * @param adminid
     * @param account
     * @param pwd
     * @param shopname
     * @param tel
     * @param username
     * @return
     */
    @PostMapping("/addshangjia")
    public Body addshangjia(String adminid, String account, String pwd, String shopname, String tel, String username, String address) {
        return adminService.addshangjia(adminid, account, pwd, shopname, tel, username, address);
    }

    /**
     * 删除账号
     *
     * @param adminid
     * @return
     */
    @PostMapping("/deleteadmin")
    public Body deleteadmin(String adminid) {
        return adminService.deleteadmin(adminid);
    }

    /**
     * 查看代理  按照城市搜索
     *
     * @param cityid
     * @return
     */
    @PostMapping("/getdailiList")
    public Body getdailiList(String cityid) {
        return adminService.getdailiList(cityid);
    }

    /**
     * 修改管理员密码
     *
     * @param adminid
     * @param oldpwd
     * @param newpwd
     * @return
     */
    @PostMapping("/changepwd")
    public Body changepwd(String adminid, String oldpwd, String newpwd) {
        return adminService.changepwd(adminid, oldpwd, newpwd);
    }

    /**
     * 修改个人信息
     *
     * @param adminid
     * @return
     */
    @PostMapping("/updateadmin")
    public Body updateadmin(String adminid, String username, String pwd, String account,String cityid) {
        return adminService.updateadmin(adminid, username, pwd, account,cityid);
    }

    /**
     * 查看详情
     *
     * @param adminid
     * @return
     */
    @PostMapping("/getadmininfo")
    public Body getadmininfo(String adminid) {
        return adminService.getadmininfo(adminid);
    }
}

