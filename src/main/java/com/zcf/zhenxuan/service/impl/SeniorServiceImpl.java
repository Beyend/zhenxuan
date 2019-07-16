package com.zcf.zhenxuan.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.zcf.zhenxuan.bean.News;
import com.zcf.zhenxuan.bean.Senior;
import com.zcf.zhenxuan.bean.User;
import com.zcf.zhenxuan.mapper.SeniorMapper;
import com.zcf.zhenxuan.mapper.UserMapper;
import com.zcf.zhenxuan.service.NewsService;
import com.zcf.zhenxuan.service.SeniorService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zcf.zhenxuan.util.DateUtils;
import com.zcf.zhenxuan.util.Hutool;
import com.zcf.zhenxuan.util.json.Body;
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
public class SeniorServiceImpl extends ServiceImpl<SeniorMapper, Senior> implements SeniorService {
    @Autowired
    NewsService newsMapper;
    @Autowired
    UserMapper userMapper;

    @Override
    public Body addsenior(String userid, String relname, String reltel, String address, String idcard, String business) {
        EntityWrapper<Senior> seniorEntityWrapper = new EntityWrapper<>();
        seniorEntityWrapper.eq("userid", userid);
        seniorEntityWrapper.eq("status", "2");
        this.delete(seniorEntityWrapper);
        Senior senior = new Senior();
        senior.setAddress(address);
        senior.setAddtime(DateUtils.formatTimeNow());
        senior.setBusiness(business);
        senior.setIdcard(idcard);
        senior.setRelname(relname);
        senior.setReltel(reltel);
        senior.setSeniorid(Hutool.getId());
        senior.setUserid(userid);
        senior.setStatus("0");
        boolean b = this.insert(senior);
        if (b) {
            return Body.BODY_200;
        }
        return Body.BODY_500;
    }

    @Override
    public Body getseniorlist(String type) {
        EntityWrapper<Senior> seniorEntityWrapper = new EntityWrapper<>();
        seniorEntityWrapper.orderBy("addtime", false);
        seniorEntityWrapper.eq("status", type);
        List<Senior> seniors = this.selectList(seniorEntityWrapper);
        if (seniors.size() > 0) {
            for (int i = 0; i < seniors.size(); i++) {
                User user = userMapper.selectById(seniors.get(i).getUserid());
                seniors.get(i).setUser(user);
            }
        }
        return Body.newInstance(seniors);
    }

    @Override
    public Body checksenior(String seniorid, String check) {
        Senior senior = this.selectById(seniorid);
        if ("1".equals(check)) {
            User user = userMapper.selectById(senior.getUserid());
            user.setSenior("Y");
            userMapper.updateById(user);
            News news = new News();
            news.setNewsid(Hutool.getId());
            news.setAddtime(DateUtils.formatTimeNow());
            news.setContent("您的审核认证通过了");
            news.setUserid(senior.getUserid());
            newsMapper.insert(news);
        }
        if ("2".equals(check)) {
            News news = new News();
            news.setNewsid(Hutool.getId());
            news.setAddtime(DateUtils.formatTimeNow());
            news.setContent("很遗憾，您的审核认证未通过，请重新提交审核");
            news.setUserid(senior.getUserid());
            newsMapper.insert(news);
        }
        senior.setStatus(check);
        boolean b = this.updateById(senior);
        if (b) {
            return Body.BODY_200;
        }
        return Body.BODY_500;
    }

    @Override
    public Body issenior(String userid) {
        EntityWrapper<Senior> seniorEntityWrapper = new EntityWrapper<>();
        seniorEntityWrapper.eq("userid", userid);
        List<Senior> seniors = this.selectList(seniorEntityWrapper);
        if (seniors.size() > 0) {
            if ("1".equals(seniors.get(0).getStatus())) {
                return Body.newInstance(200, "认证已经通过，不需要重复认证！");
            } else if ("2".equals(seniors.get(0).getStatus())) {
                return Body.newInstance(555, "认证已被拒绝，请重新认证！");
            } else {
                return Body.newInstance(666, "认证再审核中，请耐心等待！");
            }
        }

        return Body.newInstance(888, "请前往认证");
    }
}
