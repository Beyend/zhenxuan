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
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "userid")
    private String userid;
    private String type;
    private String phone;
    private String pwd;
    private String relpwd;
    private String username;
    private String userimg;
    /**
     * 签到积分
     */
    private String sign;
    private String sex;
    private String age;
    /**
     * 所属城市
     */
    private String cityid;
    /**
     * 代理人id
     */
    private String agentid;
    private String addtime;
    /**
     * 初级认证是否通过N/Y
     */
    private String primary;
    /**
     * 高级认证是否通过N/Y
     */
    private String senior;
    private String signday;
    @TableField(exist = false)
    private Chat chat;
    @TableField(exist = false)
    private String cityname;
    @TableField(exist = false)
    private String adminname;


    @Override
    protected Serializable pkVal() {
        return this.userid;
    }


}
