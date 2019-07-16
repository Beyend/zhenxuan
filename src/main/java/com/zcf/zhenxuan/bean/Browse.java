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
public class Browse extends Model<Browse> {

    private static final long serialVersionUID = 1L;

    /**
     * 反馈
     */
    @TableId
    private String browseid;
    private String content;
    private String img;
    private String userid;
    private String username;
    private String lianxi;
    private String addtime;



    @Override
    protected Serializable pkVal() {
        return this.browseid;
    }

}
