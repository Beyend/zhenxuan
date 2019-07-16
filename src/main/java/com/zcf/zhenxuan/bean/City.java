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
public class City extends Model<City> {

    private static final long serialVersionUID = 1L;
    @TableId
    private String cityid;
    private String cityname;
    private String addtime;



    @Override
    protected Serializable pkVal() {
        return this.cityid;
    }


}
