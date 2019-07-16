package com.zcf.zhenxuan.bean;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
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
@TableName("orders")
public class Order extends Model<Order> {

    private static final long serialVersionUID = 1L;
    @TableId
    private String orderid;
    private String userid;
    private String shopid;
    private String addtime;
    private String goodsid;
    private Integer num;
    private String totalmoney;
    /**
     * 0待付款  1 已付款/待发货  2 已发货/待收货   3 确认收货/待评价  4  完成 5 退款
     */
    private String status;
    private String paytime;
    private String addressid;
    private String paytype;
    private String returntype;//0退款申请 1退款成功  2 退款失败
    @TableField(exist = false)
    private Shop shop;
    @TableField(exist = false)
    private Goods goods;

    @TableField(exist = false)
    private String shopname;
    @TableField(exist = false)
    private String shoplogo;
    @TableField(exist = false)
    private String username;
    @TableField(exist = false)
    private String area;
    @TableField(exist = false)
    private String detailaddress;
    @TableField(exist = false)
    private String peisongname;
    @TableField(exist = false)
    private String telephone;
    @TableField(exist = false)
    private String goodsname;
    @TableField(exist = false)
    private String goodslogo;


    @Override
    protected Serializable pkVal() {
        return this.orderid;
    }


}
