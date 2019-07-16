package com.zcf.zhenxuan.bean;

import java.io.Serializable;

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
public class Attribute extends Model<Attribute> {

    private static final long serialVersionUID = 1L;

    /**
     * 属性
     */
    @TableId
    private String attributeid;
    private String attributename;



    @Override
    protected Serializable pkVal() {
        return this.attributeid;
    }


}
