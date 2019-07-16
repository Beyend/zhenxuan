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
public class Chat extends Model<Chat> {

    private static final long serialVersionUID = 1L;
    @TableId
    private String chatid;
    private String shopid;
    private String adminid;
    private String content;
    private String img;
    private String addtime;
    private String userid;
    /**
     * 1 商家发给客户  2  客户发给商家
     */
    private String type;
    @TableField(exist = false)
    private Shop shop;
    @TableField(exist = false)
    private User user;


    @Override
    protected Serializable pkVal() {
        return this.chatid;
    }

}
