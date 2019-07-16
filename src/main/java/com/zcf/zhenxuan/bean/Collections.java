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
public class Collections extends Model<Collections> {

    private static final long serialVersionUID = 1L;
    @TableId
    private String collectionid;
    private String goodsid;
    private String userid;
    private String addtime;
    @TableField(exist = false)
    private Goods goods;


    @Override
    protected Serializable pkVal() {
        return this.collectionid;
    }


}
