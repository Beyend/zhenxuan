package com.zcf.zhenxuan.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.zcf.zhenxuan.bean.Admin;
import com.zcf.zhenxuan.bean.Shop;
import com.zcf.zhenxuan.mapper.AdminMapper;
import com.zcf.zhenxuan.mapper.CityMapper;
import com.zcf.zhenxuan.mapper.OrderMapper;
import com.zcf.zhenxuan.mapper.ShopMapper;
import com.zcf.zhenxuan.service.AdminService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zcf.zhenxuan.util.DateUtils;
import com.zcf.zhenxuan.util.Hutool;
import com.zcf.zhenxuan.util.json.Body;
import com.zcf.zhenxuan.vo.out.OrderTotalVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author kmr123
 * @since 2018-12-18
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
    @Autowired
    AdminMapper adminMapper;
    @Autowired
    ShopMapper shopMapper;
    @Autowired
    CityMapper cityMapper;
    @Autowired
    OrderMapper orderMapper;

    @Override
    public Body adddaili(String account, String pwd, String cityid) {
        EntityWrapper<Admin> adminEntityWrapper = new EntityWrapper<>();
        adminEntityWrapper.eq("account", account);
        List<Admin> admins = adminMapper.selectList(adminEntityWrapper);
        if (admins.size() > 0) {
            return Body.newInstance(777, "账号已存在，不能重复注册！");
        }
        Admin admin = new Admin();
        admin.setAdminid(Hutool.getId());
        admin.setAccount(account);
        admin.setCityid(cityid);
        admin.setType("1");
        admin.setPwd(pwd);
        admin.setAddtime(DateUtils.formatTimeNow());
        Integer insert = adminMapper.insert(admin);
        if (insert > 0) {
            return Body.BODY_200;
        }
        return Body.BODY_500;
    }

    @Override
    public Body addshangjia(String adminid, String account, String pwd, String shopname, String tel, String username, String address) {
        Admin admin1 = adminMapper.selectById(adminid);
        EntityWrapper<Admin> adminEntityWrapper = new EntityWrapper<>();
        adminEntityWrapper.eq("account", account);
        List<Admin> admins = adminMapper.selectList(adminEntityWrapper);
        if (admins.size() > 0) {
            return Body.newInstance(777, "账号已存在，不能重读注册！");
        }
        Admin admin = new Admin();
        admin.setAdminid(Hutool.getId());
        admin.setPwd(pwd);
        admin.setType("2");
        admin.setAccount(account);
        admin.setAddtime(DateUtils.formatTimeNow());
        if (admin1 != null) {
            admin.setCityid(admin1.getCityid());
        }
        Integer insert = adminMapper.insert(admin);
        if (insert > 0) {
            Shop shop = new Shop();
            shop.setShopid(Hutool.getId());
            shop.setShopname(shopname);
            shop.setTel(tel);
            shop.setUsername(username);
            shop.setAddtime(DateUtils.formatTimeNow());
            shop.setAdminid(admin.getAdminid());
            shop.setIsdelete("N");
            shop.setAddress(address);
            if (admin1 != null) {
                shop.setCityid(admin1.getCityid());
            }
            shopMapper.insert(shop);
        }


        return Body.BODY_500;
    }

    @Override
    public Body loginadmin(String account, String pwd) {
        EntityWrapper<Admin> adminEntityWrapper = new EntityWrapper<>();
        adminEntityWrapper.eq("account", account);
        List<Admin> admins = adminMapper.selectList(adminEntityWrapper);
        if (admins.size() > 0) {
            if (admins.get(0).getPwd().equals(pwd)) {
                return Body.newInstance(admins.get(0));
            } else {
                return Body.newInstance(555, "密码错误！");
            }
        } else {
            return Body.newInstance(777, "账号不存在!");

        }
    }

    @Override
    public Body deleteadmin(String adminid) {
        Integer integer = adminMapper.deleteById(adminid);
        if (integer > 0) {
            return Body.BODY_200;
        }
        return Body.BODY_500;
    }

    @Override
    public Body getdailiList(String cityid) {
        EntityWrapper<Admin> adminEntityWrapper = new EntityWrapper<>();
        adminEntityWrapper.eq("type", "1");
        adminEntityWrapper.orderBy("addtime", false);
        if (!StringUtils.isEmpty(cityid)) {
            adminEntityWrapper.eq("cityid", cityid);
        }
        List<Admin> admins = adminMapper.selectList(adminEntityWrapper);

        EntityWrapper<Admin> adminWrapper = new EntityWrapper<>();
        adminWrapper.eq("type", "2");
        if (!StringUtils.isEmpty(cityid)) {
            adminWrapper.eq("cityid", cityid);
        }
        List<Admin> adminList = adminMapper.selectList(adminWrapper);//商家



        if (admins.size() > 0) {
            Admin admin = null;
            for (int i = 0; i < admins.size(); i++) {
                admins.get(i).setCityname(cityMapper.selectById(admins.get(i).getCityid()).getCityname());
                admin = admins.get(i);
                OrderTotalVo total =orderMapper.getAdminTotal(admin.getCityid());//代理成交数量和金额
                if(total != null){
                    admin.setTotalnum(total.getTotalnum());
                    admin.setTotalmoney(total.getTotalmoney());
                }else{
                    admin.setTotalnum(0);
                    admin.setTotalmoney("0");
                }
            }
        }
        return Body.newInstance(admins);
    }

    @Override
    public Body changepwd(String adminid, String oldpwd, String newpwd) {
        Admin admin = adminMapper.selectById(adminid);
        if (admin.getPwd().equals(oldpwd)) {
            admin.setPwd(newpwd);
            Integer integer = adminMapper.updateById(admin);
            if (integer > 0) {
                return Body.BODY_200;
            }
            return Body.BODY_500;
        } else {
            return Body.newInstance(777, "原密码不正确!");
        }
    }

    @Override
    public Body updateadmin(String adminid, String username, String pwd, String account, String cityid) {
        Admin admin = adminMapper.selectById(adminid);
        if (admin != null) {
            if (!StringUtils.isEmpty(username)) {
                admin.setUsername(username);
            }
            if (!StringUtils.isEmpty(pwd)) {
                admin.setPwd(pwd);
            }
            if (!StringUtils.isEmpty(account)) {
                admin.setAccount(account);
            }
            if (!StringUtils.isEmpty(cityid)) {
                admin.setCityid(cityid);
            }
            Integer integer = adminMapper.updateById(admin);
            if (integer > 0) {
                return Body.BODY_200;
            }
        }

        return Body.BODY_500;
    }

    @Override
    public Body getadmininfo(String adminid) {
        Admin admin = adminMapper.selectById(adminid);
        return Body.newInstance(admin);
    }


}
