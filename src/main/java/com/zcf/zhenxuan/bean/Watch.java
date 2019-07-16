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
public class Watch extends Model<Watch> {

    private static final long serialVersionUID = 1L;

    /**
     * 浏览记录
     */
    @TableId
    private String watchid;
    /**
     * 1商品浏览 2 同城浏览
     */
    private String type;
    private String seeid;
    private String userid;
    private String addtime;
    @TableField(exist = false)
    private Goods goods;
    @TableField(exist = false)
    private Samecity samecity;


    @Override
    protected Serializable pkVal() {
        return this.watchid;
    }


}
