package com.zcf.zhenxuan.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.zcf.zhenxuan.bean.Title;
import com.zcf.zhenxuan.mapper.TitleMapper;
import com.zcf.zhenxuan.service.TitleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zcf.zhenxuan.util.DateUtils;
import com.zcf.zhenxuan.util.Hutool;
import com.zcf.zhenxuan.util.json.Body;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author kmr123
 * @since 2019-01-18
 */
@Service
public class TitleServiceImpl extends ServiceImpl<TitleMapper, Title> implements TitleService {
    @Autowired
    private TitleMapper titleMapper;

    @Override
    public Body addtitle(String content) {
        Title title = new Title();
        title.setTitleid(Hutool.getId());
        title.setAddtime(DateUtils.formatTimeNow());
        title.setContent(content);
        Integer insert = titleMapper.insert(title);
        if (insert > 0) {
            return Body.BODY_200;
        }
        return Body.BODY_500;


    }

    @Override
    public Body updatetitle(String titleid, String content) {
        Title title = this.selectById(titleid);
        if (!StringUtils.isEmpty(content)) {
            title.setContent(content);
        }
        boolean b = this.updateById(title);
        if (b) {
            return Body.BODY_200;
        }
        return Body.BODY_500;
    }

    @Override
    public Body deletetitle(String titleid) {
        boolean b = this.deleteById(titleid);
        if (b) {
            return Body.BODY_200;
        }
        return Body.BODY_500;
    }

    @Override
    public Body gettitleinfo(String titleid) {
        Title title = this.selectById(titleid);

        return Body.newInstance(title);
    }

    @Override
    public Body gettitlelist() {
        EntityWrapper<Title> titleEntityWrapper = new EntityWrapper<>();
        titleEntityWrapper.orderBy("addtime", false);
        List<Title> titles = this.selectList(titleEntityWrapper);

        return Body.newInstance(titles);
    }
}
