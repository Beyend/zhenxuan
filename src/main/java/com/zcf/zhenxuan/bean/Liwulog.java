package com.zcf.zhenxuan.bean;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
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
public class Liwulog extends Model<Liwulog> {

    private static final long serialVersionUID = 1L;
    @TableId
    private String liwulogid;
    private String userid;
    private String setid;
    private String zhongjiangmongth;

    private String   lianxi;
    private String   tel;
    @TableField(exist = false)
    private User user;
    @TableField(exist = false)
    private Settings settings;



    @Override
    protected Serializable pkVal() {
        return this.liwulogid;
    }


}
