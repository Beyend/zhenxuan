package com.zcf.zhenxuan.service;

import com.zcf.zhenxuan.bean.Address;
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
public interface AddressService extends IService<Address> {

    Body addaddress(String userid, String area, String detailaddress, String username, String telephone, String type);

    Body updateaddress(String addressid, String area, String detailaddress, String username, String telephone, String type);

    Body deleteaddress(String addressid);

    Body getaddress(String addressid);

    Body getaddressList(String userid);
}
