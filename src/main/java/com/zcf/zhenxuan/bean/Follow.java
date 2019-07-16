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
public class Follow extends Model<Follow> {

    private static final long serialVersionUID = 1L;

    /**
     * 关注
     */
    @TableId
    private String followid;
    private String shopid;
    private String addtime;
    private String userid;
    @TableField(exist = false)
    private Shop shop;


    @Override
    protected Serializable pkVal() {
        return this.followid;
    }


}
