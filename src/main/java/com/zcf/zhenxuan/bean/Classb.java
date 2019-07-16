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
public class Classb extends Model<Classb> {

    private static final long serialVersionUID = 1L;

    /**
     * 二级分类
     */
    @TableId
    private String classbid;
    private String classbname;
    private String classbimg;
    private String addtime;
    private String classaid;
    @TableField(exist = false)
    private String totolnum;
    @TableField(exist = false)
    private String totolmomey;
    @TableField(exist = false)
    private String monthnum;
    @TableField(exist = false)
    private String monthmoney;




    @Override
    protected Serializable pkVal() {
        return this.classbid;
    }


}
