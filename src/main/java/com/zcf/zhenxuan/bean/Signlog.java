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
 * @since 2019-01-16
 */
@Data
public class Signlog extends Model<Signlog> {

    private static final long serialVersionUID = 1L;
    @TableId
    private String signlogid;
    private String userid;
    private String signtime;



    @Override
    protected Serializable pkVal() {
        return this.signlogid;
    }


}
