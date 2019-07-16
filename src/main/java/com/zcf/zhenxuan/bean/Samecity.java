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
 * @since 2018-12-19
 */
@Data
public class Samecity extends Model<Samecity> {

    private static final long serialVersionUID = 1L;
    @TableId
    private String samecityid;
    private String userid;
    /**
     * 招聘岗位/求职意向/出租楼盘名称/出租楼盘名称/维修标题/配送标题
     */
    private String title;
    /**
     * 招聘职位描述/求职工作经历/维修描述/配送描述
     */
    private String content;
    private String addtime;
    /**
     * 招聘图片/简历头像/房屋出租图片/出售图片
     */
    private String sameimg;
    /**
     * 出租房屋配置/标签
     */
    private String label;
    /**
     * 招聘薪资范围/求职薪资要求/出租租金/出售售价
     */
    private String price;
    /**
     * 出租面积/出售面积
     */
    private String metre;
    /**
     * 1维修  2 买卖  3  租赁  4 快递 5求职  6  招聘
     */
    private String type;
    /**
     * N /Y是否设为推荐
     */
    private String tuijian;
    private String uname;
    private String tell;
    private String startpath;
    private String endpath;
    private String sex;
    /**
     * 工作年限/年龄
     */
    private String age;
    /**
     * 地址详细/工作地点/出租楼盘地址/出租楼盘地址
     */
    private String address;

    /**
     * 福利待遇
     */
    private String daiyu;
    /**
     * 学历要求
     */
    private String xueli;
    /**
     * 出租区域/出售区域
     */
    private String area;
    /**
     * 楼层
     */
    private String louceng;
    /**
     * 户型
     */
    private String huxing;
    /**
     * 车库
     */
    private String cheku;
    /**
     * 储藏室
     */
    private String chucang;
    /**
     * 装修情况
     */
    private String zhuangxiu;
    /**
     * 朝向
     */
    private String chaoxiang;
    /**
     * 房源介绍
     */
    private String fangyuanjieshao;
    /**
     * 出租付款方式
     */
    private String paytype;
    /**
     * 出售房屋证件
     */
    private String zhengjian;


    @TableField(exist = false)
    private User user;

    @Override
    protected Serializable pkVal() {
        return this.samecityid;
    }


}
