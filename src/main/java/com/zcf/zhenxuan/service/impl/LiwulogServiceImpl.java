package com.zcf.zhenxuan.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zcf.zhenxuan.bean.Liwulog;
import com.zcf.zhenxuan.bean.Settings;
import com.zcf.zhenxuan.bean.User;
import com.zcf.zhenxuan.controller.util.StringUtil;
import com.zcf.zhenxuan.mapper.LiwulogMapper;
import com.zcf.zhenxuan.mapper.SettingMapper;
import com.zcf.zhenxuan.mapper.UserMapper;
import com.zcf.zhenxuan.service.LiwulogService;
import com.zcf.zhenxuan.util.json.Body;
import com.zcf.zhenxuan.vo.in.UserParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author kmr123
 * @since 2019-01-16
 */
@Service
public class LiwulogServiceImpl extends ServiceImpl<LiwulogMapper, Liwulog> implements LiwulogService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    LiwulogMapper liwulogMapper;
    @Autowired
    SettingMapper settingMapper;

    @Override
    public Body getLiwuloglist(UserParam param) {

        Page<Liwulog> page = new Page<Liwulog>(param.getPageNum(), param.getPageSize());
        EntityWrapper<Liwulog> liwulogEntityWrapper = new EntityWrapper<>();
        if (!StringUtils.isEmpty(param.getType())) {
            liwulogEntityWrapper.eq("setid", param.getType());
        }
        if (!StringUtils.isEmpty(param.getMonth())) {
            liwulogEntityWrapper.eq("zhongjiangmongth", param.getMonth());
        }
        liwulogEntityWrapper.orderBy("zhongjiangmongth", false);
        List<Liwulog> liwulogs = this.selectList(liwulogEntityWrapper);

        List<Liwulog> Liwuloglist = liwulogMapper.selectPage(page, liwulogEntityWrapper);
        if (Liwuloglist.size() > 0) {
            for (int i = 0; i < Liwuloglist.size(); i++) {
                User user = userMapper.selectById(Liwuloglist.get(i).getUserid());
                Liwuloglist.get(i).setUser(user);
                Settings settings = settingMapper.selectById(Liwuloglist.get(i).getSetid());
                Liwuloglist.get(i).setSettings(settings);
            }
        }

        Map<Object, Object> map = new HashMap<>();
        map.put("total", liwulogs.size());
        map.put("data", Liwuloglist);
        return Body.newInstance(map);


    }

    @Override
    public Body zhongjiang(String liwulogid, String lianxi, String tel) {
        Liwulog liwulog = this.selectById(liwulogid);
        liwulog.setLianxi(lianxi);
        liwulog.setTel(tel);
        boolean b = this.updateById(liwulog);
        if (b) {
            return Body.BODY_200;
        }
        return Body.BODY_500;
    }
}
