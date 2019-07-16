package com.zcf.zhenxuan.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zcf.zhenxuan.bean.Address;
import com.zcf.zhenxuan.mapper.AddressMapper;
import com.zcf.zhenxuan.service.AddressService;
import com.zcf.zhenxuan.util.DateUtils;
import com.zcf.zhenxuan.util.Hutool;
import com.zcf.zhenxuan.util.json.Body;
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
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {

    @Autowired
    AddressMapper addressMapper;

    @Override
    public Body addaddress(String userid, String area, String detailaddress, String username, String telephone, String type) {
        Address address = new Address();
        address.setAddressid(Hutool.getId());
        address.setArea(area);
        address.setDetailaddress(detailaddress);
        address.setTelephone(telephone);
        address.setUserid(userid);
        address.setUsername(username);
        address.setType(type);
        address.setAddtime(DateUtils.formatTimeNow());
        if ("1".equals(type)) {
            EntityWrapper<Address> addressEntityWrapper = new EntityWrapper<>();
            addressEntityWrapper.eq("type", "1");
            List<Address> list = addressMapper.selectList(addressEntityWrapper);
            if (list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setType("0");
                    addressMapper.updateById(list.get(i));
                }
            }
        }
        Integer insert = addressMapper.insert(address);
        if (insert > 0) {
            return Body.BODY_200;
        }
        return Body.BODY_500;
    }

    @Override
    public Body updateaddress(String addressid, String area, String detailaddress, String username, String telephone, String type) {
        Address address = addressMapper.selectById(addressid);
        address.setType(type);
        address.setUsername(username);
        address.setTelephone(telephone);
        address.setDetailaddress(detailaddress);
        address.setArea(area);
        if ("1".equals(type)) {
            EntityWrapper<Address> addressEntityWrapper = new EntityWrapper<>();
            addressEntityWrapper.eq("type", "1");
            List<Address> list = addressMapper.selectList(addressEntityWrapper);
            if (list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setType("0");
                    addressMapper.updateById(list.get(i));
                }
            }
        }
        Integer integer = addressMapper.updateById(address);
        if (integer > 0) {
            return Body.BODY_200;
        }
        return Body.BODY_500;
    }

    @Override
    public Body deleteaddress(String addressid) {
        Integer integer = addressMapper.deleteById(addressid);
        if (integer > 0) {
            return Body.BODY_200;
        }
        return Body.BODY_500;
    }

    @Override
    public Body getaddress(String addressid) {
        Address address = addressMapper.selectById(addressid);
        if (address != null) {
            return Body.newInstance(address);
        }
        return Body.BODY_500;
    }

    @Override
    public Body getaddressList(String userid) {
        EntityWrapper<Address> addressEntityWrapper = new EntityWrapper<>();
        addressEntityWrapper.eq("userid", userid);
        addressEntityWrapper.orderBy("type", false);
        addressEntityWrapper.orderBy("addtime", false);
        List<Address> addresses = addressMapper.selectList(addressEntityWrapper);
        return Body.newInstance(addresses);
    }
}
