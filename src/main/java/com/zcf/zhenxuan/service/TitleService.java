package com.zcf.zhenxuan.service;

import com.zcf.zhenxuan.bean.Title;
import com.baomidou.mybatisplus.service.IService;
import com.zcf.zhenxuan.util.json.Body;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kmr123
 * @since 2019-01-18
 */
public interface TitleService extends IService<Title> {

    Body addtitle(String content);

    Body updatetitle(String titleid, String content);

    Body deletetitle(String titleid);

    Body gettitleinfo(String titleid);

    Body gettitlelist();
}
