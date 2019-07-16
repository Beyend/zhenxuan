package com.zcf.zhenxuan.service;

import com.zcf.zhenxuan.bean.News;
import com.baomidou.mybatisplus.service.IService;
import com.zcf.zhenxuan.util.json.Body;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kmr123
 * @since 2018-12-18
 */
public interface NewsService extends IService<News> {

    Body getnewslist(String userid);
}
