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
public class Admin extends Model<Admin> {

    private static final long serialVersionUID = 1L;
    @TableId
    private String adminid;
    /**
     * 0超级管理人 1城市合伙人  2  商家管理人
     */
    private String type;
    private String addtime;
    private String account;
    private String pwd;
    private String cityid;
    private String username;
    @TableField(exist = false)
    private String cityname;
    @TableField(exist = false)
    private Integer totalnum;
    @TableField(exist = false)
    private String totalmoney;

    @Override
    protected Serializable pkVal() {
        return this.adminid;
    }


}
