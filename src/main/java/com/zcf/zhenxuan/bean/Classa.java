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
public class Classa extends Model<Classa> {

    private static final long serialVersionUID = 1L;

    /**
     * 一级分类
     */
    @TableId
    private String classaid;
    private String classaname;
    private String addtime;
    private String attributeid;




    @Override
    protected Serializable pkVal() {
        return this.classaid;
    }


}
