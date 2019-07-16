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
public class Senior extends Model<Senior> {

    private static final long serialVersionUID = 1L;

    /**
     * 高级认证
     */
    @TableId
    private String seniorid;
    private String userid;
    private String relname;
    private String reltel;
    private String address;
    /**
     * 身份证正面照
     */
    private String idcard;
    /**
     * 营业执照
     */
    private String business;
    /**
     * 0审核中  1通过  2未通过
     */
    private String status;
    private String addtime;

    @TableField(exist = false)
    private User user;


    @Override
    protected Serializable pkVal() {
        return this.seniorid;
    }


}
