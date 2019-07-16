package com.zcf.zhenxuan.bean;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
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
public class Settings extends Model<Settings> {

    private static final long serialVersionUID = 1L;
    @TableId
    private String setid;
    private String liwu;
    private String type;
    private String num;



    @Override
    protected Serializable pkVal() {
        return this.setid;
    }


}
