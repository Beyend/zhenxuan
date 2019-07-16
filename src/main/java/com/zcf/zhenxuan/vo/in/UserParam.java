package com.zcf.zhenxuan.vo.in;

import com.zcf.zhenxuan.bean.Shop;
import com.zcf.zhenxuan.bean.User;
import lombok.Data;

@Data
public class UserParam extends PageVo {
    private String userid;
    private String cityid;
    private String shopisdelete;
    private String attributeid;
    private String shopname;
    private String goodsname;
    private String type;
    private String search;
    private String classbid;
    private String shopid;
    private String status;
    private String month;
    private String begDate;
    private String endDate;

}
