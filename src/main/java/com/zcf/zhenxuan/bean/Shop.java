package com.zcf.zhenxuan.bean;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
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
public class Shop extends Model<Shop> {

    private static final long serialVersionUID = 1L;
    @TableId
    private String shopid;
    private String shoplogo;
    private String shopname;
    /**
     * 介绍
     */
    private String introduce;
    private String address;
    private String tel;
    /**
     * 配送时间
     */
    private String deltime;
    private String shopimg;
    private String cityid;
    /**
     * 属性
     */
    private String attributeid;
    private String isdelete;
    private String username;
    private String addtime;
    private String adminid;//拥有着
    @TableField(exist = false)
    private String isfollow;
    @TableField(exist = false)
    private Chat chat;
    @TableField(exist = false)
    private String cityname;
    @TableField(exist = false)
    private Integer totalnum;
    @TableField(exist = false)
    private String totalmoney;
    @TableField(exist = false)
    private String refund;

    @Override
    protected Serializable pkVal() {
        return this.shopid;
    }


}
