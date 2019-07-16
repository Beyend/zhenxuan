package com.zcf.zhenxuan.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zcf.zhenxuan.bean.News;
import com.zcf.zhenxuan.bean.Settings;
import com.zcf.zhenxuan.bean.Signlog;
import com.zcf.zhenxuan.bean.User;
import com.zcf.zhenxuan.mapper.NewsMapper;
import com.zcf.zhenxuan.mapper.SettingMapper;
import com.zcf.zhenxuan.mapper.SignlogMapper;
import com.zcf.zhenxuan.mapper.UserMapper;
import com.zcf.zhenxuan.service.SettingService;
import com.zcf.zhenxuan.service.SignlogService;
import com.zcf.zhenxuan.util.DateUtils;
import com.zcf.zhenxuan.util.Hutool;
import com.zcf.zhenxuan.util.json.Body;
import org.apache.commons.lang3.ObjectUtils;
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
 * @since 2019-01-16
 */
@Service
public class SettingServiceImpl extends ServiceImpl<SettingMapper, Settings> implements SettingService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    NewsMapper newsMapper;

    @Override
    public Body addSetting(String liwu, String type, String num) {
        Settings setting = new Settings();
        setting.setSetid(Hutool.getId());
        setting.setLiwu(liwu);
        setting.setNum(num);
        setting.setType(type);
        boolean insert = this.insert(setting);
        if (insert) {
            return Body.BODY_200;
        }
        return Body.BODY_500;
    }

    @Override
    public Body changeSetting(String setid, String liwu, String type, String num) {

        Settings setting = this.selectById(setid);
        if (!StringUtils.isEmpty(type)) {
            setting.setType(type);
        }
        if (!StringUtils.isEmpty(liwu)) {
            setting.setLiwu(liwu);
        }
        if (!StringUtils.isEmpty(num)) {
            setting.setNum(num);
        }

        boolean b = this.updateById(setting);
        if (b) {
            EntityWrapper<User> userEntityWrapper = new EntityWrapper<>();
            List<User> users = userMapper.selectList(userEntityWrapper);
            if (users.size() > 0) {
                for (int i = 0; i < users.size(); i++) {
                    News news = new News();
                    if (type.equals("1")) {
                        news.setContent("连续签到7天，获的礼品人数上线为" + num + "人。");
                    } else if (type.equals("2")) {
                        news.setContent("连续签到14天，获的礼品人数上线为" + num + "人。");
                    } else if (type.equals("3")) {
                        news.setContent("连续签到21天，获的礼品人数上线为" + num + "人。");
                    } else {
                        news.setContent("连续签到28天，获的礼品人数上线为" + num + "人。");
                    }
                    news.setAddtime(DateUtils.formatTimeNow());
                    news.setUserid(users.get(i).getUserid());
                    news.setNewsid(Hutool.getId());
                    newsMapper.insert(news);
                }
            }

            return Body.BODY_200;
        }
        return Body.BODY_500;
    }

    @Override
    public Body getSettingInfo(String setid) {
        if (!StringUtils.isEmpty(setid)) {
            Settings setting = this.selectById(setid);
            return Body.newInstance(setting);
        }
        return Body.newInstance(null);

    }
}
