package com.zcf.zhenxuan.service.impl;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.zcf.zhenxuan.bean.Signlog;
import com.zcf.zhenxuan.mapper.SignlogMapper;
import com.zcf.zhenxuan.service.SignlogService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zcf.zhenxuan.util.DateUtils;
import com.zcf.zhenxuan.util.json.Body;
import org.springframework.stereotype.Service;

import java.util.Calendar;
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
public class SignlogServiceImpl extends ServiceImpl<SignlogMapper, Signlog> implements SignlogService {


    @Override
    public Body getSingBy(String userid) {

        EntityWrapper<Signlog> signlogEntityWrapper = new EntityWrapper<>();
        signlogEntityWrapper.eq("userid", userid);
        signlogEntityWrapper.like("signtime",DateUtils.formatDateNow().substring(0,7));
        signlogEntityWrapper.orderBy("signtime", false);
        List<Signlog> signlogs = this.selectList(signlogEntityWrapper);
        return Body.newInstance(signlogs);
    }



}
