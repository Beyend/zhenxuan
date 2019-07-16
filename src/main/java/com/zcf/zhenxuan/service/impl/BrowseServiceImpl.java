package com.zcf.zhenxuan.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zcf.zhenxuan.bean.Browse;
import com.zcf.zhenxuan.bean.User;
import com.zcf.zhenxuan.mapper.BrowseMapper;
import com.zcf.zhenxuan.service.BrowseService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zcf.zhenxuan.util.DateUtils;
import com.zcf.zhenxuan.util.Hutool;
import com.zcf.zhenxuan.util.json.Body;
import com.zcf.zhenxuan.vo.in.UserParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author kmr123
 * @since 2018-12-18
 */
@Service
public class BrowseServiceImpl extends ServiceImpl<BrowseMapper, Browse> implements BrowseService {

    @Autowired
    BrowseMapper browseMapper;

    @Override
    public Body addbrowse(String content, String img, String userid, String username, String lianxi) {
        Browse browse = new Browse();
        browse.setBrowseid(Hutool.getId());
        browse.setAddtime(DateUtils.formatTimeNow());
        browse.setContent(content);
        if (!StringUtils.isEmpty(img)) {
            browse.setImg(img);
        }
        browse.setUserid(userid);
        browse.setUsername(username);
        browse.setLianxi(lianxi);
        boolean b = this.insert(browse);
        if (b) {
            return Body.BODY_200;
        }
        return Body.BODY_500;
    }

    @Override
    public Body getbrowse(UserParam param) {
        Page<Browse> page = new Page<Browse>(param.getPageNum(), param.getPageSize());
        EntityWrapper<Browse> eWrapper = new EntityWrapper<Browse>();
        eWrapper.orderBy("addtime", false);
        List<Browse> browses = browseMapper.selectList(eWrapper);
        List<Browse> browselist = browseMapper.selectPage(page, eWrapper);
        Map<Object, Object> map = new HashMap<>();
        map.put("total", browses.size());
        map.put("data", browselist);
        return Body.newInstance(map);
    }
}
