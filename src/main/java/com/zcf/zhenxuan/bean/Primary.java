package com.zcf.zhenxuan.bean;

import java.io.Serializable;

import java.util.Date;

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
@TableName("primarys")
public class Primary extends Model<Primary> {

    private static final long serialVersionUID = 1L;

    /**
     * 初级认证
     */
    @TableId
    private String primaryid;
    private String userid;
    private String relname;
    private String reltel;
    private String addtime;
    /**
     * 0审核中  1通过  2未通过
     */
    private String status;
    private String address;
    @TableField(exist = false)
    private User user;


    @Override
    protected Serializable pkVal() {
        return this.primaryid;
    }


}
