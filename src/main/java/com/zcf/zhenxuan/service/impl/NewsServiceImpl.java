package com.zcf.zhenxuan.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.zcf.zhenxuan.bean.News;
import com.zcf.zhenxuan.mapper.NewsMapper;
import com.zcf.zhenxuan.service.NewsService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zcf.zhenxuan.util.json.Body;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author kmr123
 * @since 2018-12-18
 */
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements NewsService {

    @Override
    public Body getnewslist(String userid) {
        EntityWrapper<News> newsEntityWrapper = new EntityWrapper<>();
        newsEntityWrapper.eq("userid", userid);
        newsEntityWrapper.orderBy("addtime", false);
        List<News> news = this.selectList(newsEntityWrapper);

        return Body.newInstance(news);
    }
}
