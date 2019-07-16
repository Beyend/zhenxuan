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
public class Evaluate extends Model<Evaluate> {

    private static final long serialVersionUID = 1L;
    @TableId
    private String evaluateid;
    private String orderid;
    private String goodsid;
    private String shopid;
    private String userid;
    private String addtime;
    private String score;
    private String content;
    private String img;




    @Override
    protected Serializable pkVal() {
        return this.evaluateid;
    }


}
