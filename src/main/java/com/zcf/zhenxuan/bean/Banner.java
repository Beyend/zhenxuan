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
public class Banner extends Model<Banner> {

    private static final long serialVersionUID = 1L;
    @TableId
    private String bannerid;
    private String cityid;
    private String bannerimg;
    private String goodsid;
    private int level;
    @TableField(exist = false)
    private String cityname;
    @TableField(exist = false)
    private String goodsname;


    @Override

    protected Serializable pkVal() {
        return this.bannerid;
    }


}
