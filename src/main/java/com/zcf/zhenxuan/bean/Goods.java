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
public class Goods extends Model<Goods> {

    private static final long serialVersionUID = 1L;
    @TableId
    private String goodsid;
    private String goodsname;
    private String goodslogo;
    private String classbid;
    private String shopid;
    /**
     * 商家详细文字描述
     */
    private String introduce;
    private Integer num;
    private String price;
    /**
     * 秒杀商品价格
     */
    private String spike;
    /**
     * 1普通商品  2 秒杀商品
     */
    private String type;
    /**
     * 秒杀商品是否置顶N/Y
     */
    private String top;
    /**
     * 秒杀商品置顶排序
     */
    private String toplevel;
    private String isdelete;
    private String addtime;
    private String tuijian;//1即为推荐
    private Integer paynum;
    @TableField(exist = false)
    private String iscollection;
    @TableField(exist = false)
    private Shop shop;
    @TableField(exist = false)
    private Classb classb;
    @TableField(exist = false)
    private String classbname;


    @Override
    protected Serializable pkVal() {
        return this.goodsid;
    }


}
