package com.zcf.zhenxuan.service;

import com.zcf.zhenxuan.bean.Admin;
import com.baomidou.mybatisplus.service.IService;
import com.zcf.zhenxuan.util.json.Body;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kmr123
 * @since 2018-12-18
 */
public interface AdminService extends IService<Admin> {

    Body adddaili(String account, String pwd, String cityid);

    Body addshangjia(String adminid, String account, String pwd, String shopname, String tel, String username,String address);

    Body loginadmin(String account, String pwd);

    Body deleteadmin(String adminid);

    Body getdailiList(String cityid);

    Body changepwd(String adminid, String oldpwd, String newpwd);

    Body updateadmin(String adminid, String username, String pwd, String account, String cityid);

    Body getadmininfo(String adminid);
}
