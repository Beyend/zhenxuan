package com.zcf.zhenxuan.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zcf.zhenxuan.bean.News;
import com.zcf.zhenxuan.bean.Primary;
import com.zcf.zhenxuan.bean.User;
import com.zcf.zhenxuan.mapper.NewsMapper;
import com.zcf.zhenxuan.mapper.PrimaryMapper;
import com.zcf.zhenxuan.mapper.UserMapper;
import com.zcf.zhenxuan.service.PrimaryService;
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
 * @since 2018-12-18
 */
@Service
public class PrimaryServiceImpl extends ServiceImpl<PrimaryMapper, Primary> implements PrimaryService {
    @Autowired
    NewsMapper newsMapper;
    @Autowired
    UserMapper userMapper;

    @Override
    public Body addprimary(String userid, String relname, String reltel, String address) {
        EntityWrapper<Primary> primaryEntityWrapper = new EntityWrapper<>();
        primaryEntityWrapper.eq("userid", userid);
        primaryEntityWrapper.eq("status", "2");
        this.delete(primaryEntityWrapper);
        Primary primary = new Primary();
        primary.setPrimaryid(Hutool.getId());
        primary.setAddress(address);
        primary.setRelname(relname);
        primary.setAddtime(DateUtils.formatTimeNow());
        primary.setReltel(reltel);
        primary.setStatus("0");
        primary.setUserid(userid);
        boolean b = this.insert(primary);
        if (b) {
            return Body.BODY_200;
        }
        return Body.BODY_500;
    }

    @Override
    public Body checkprimary(String primaryid, String check) {
        Primary primary = this.selectById(primaryid);
        if ("1".equals(check)) {

            User user = userMapper.selectById(primary.getUserid());
            user.setPrimary("Y");
            userMapper.updateById(user);
            News news = new News();
            news.setNewsid(Hutool.getId());
            news.setAddtime(DateUtils.formatTimeNow());
            news.setContent("您的审核认证通过了");
            news.setUserid(primary.getUserid());
            newsMapper.insert(news);
        }
        if ("2".equals(check)) {
            News news = new News();
            news.setNewsid(Hutool.getId());
            news.setAddtime(DateUtils.formatTimeNow());
            news.setContent("很遗憾，您的审核认证未通过，请重新提交审核");
            news.setUserid(primary.getUserid());
            newsMapper.insert(news);
        }
        primary.setStatus(check);
        boolean b = this.updateById(primary);
        if (b) {
            return Body.BODY_200;
        }
        return Body.BODY_500;
    }

    @Override
    public Body getprimary(String type) {
        EntityWrapper<Primary> primaryEntityWrapper = new EntityWrapper<>();
        if (!StringUtils.isEmpty(type)) {
            primaryEntityWrapper.eq("status", type);
        }
        primaryEntityWrapper.orderBy("addtime", false);
        List<Primary> primaries = this.selectList(primaryEntityWrapper);
        if (primaries.size() > 0) {
            for (int i = 0; i < primaries.size(); i++) {
                User user = userMapper.selectById(primaries.get(i).getUserid());
                primaries.get(i).setUser(user);
            }
        }
        return Body.newInstance(primaries);
    }

    @Override
    public Body isprimary(String userid) {
        EntityWrapper<Primary> primaryEntityWrapper = new EntityWrapper<>();
        primaryEntityWrapper.eq("userid", userid);
        List<Primary> primaries = this.selectList(primaryEntityWrapper);
        if (primaries.size() > 0) {
            if ("1".equals(primaries.get(0).getStatus())) {
                return Body.newInstance(200, "认证已经通过，不需要重复认证！");
            } else if ("2".equals(primaries.get(0).getStatus())) {
                return Body.newInstance(555, "认证已被拒绝，请重新认证！");
            } else {
                return Body.newInstance(666, "认证再审核中，请耐心等待！");
            }
        }

        return Body.newInstance(888, "请前往认证");
    }
}
