package com.zcf.zhenxuan.bean;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author kmr123
 * @since 2018-12-18
 */
@Data
@TableName("address")
public class Address extends Model<Address> {

    private static final long serialVersionUID = 1L;
    @TableId
    private String addressid;
    private String province;
    private String city;
    private String area;
    private String userid;
    private String detailaddress;
    private String username;
    private String telephone;
    /**
     * 0普通地址   1  默认收货地址
     */
    private String type;
    private String addtime;


    @Override
    protected Serializable pkVal() {
        return this.addressid;
    }


}
