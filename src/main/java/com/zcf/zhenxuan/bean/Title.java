package com.zcf.zhenxuan.bean;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author kmr123
 * @since 2019-01-18
 */
public class Title extends Model<Title> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "titleid")
    private String titleid;
    private String content;
    private String addtime;


    public String getTitleid() {
        return titleid;
    }

    public void setTitleid(String titleid) {
        this.titleid = titleid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    @Override
    protected Serializable pkVal() {
        return this.titleid;
    }

    @Override
    public String toString() {
        return "Title{" +
        ", titleid=" + titleid +
        ", content=" + content +
        ", addtime=" + addtime +
        "}";
    }
}
