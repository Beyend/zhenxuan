package com.zcf.zhenxuan.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.zcf.zhenxuan.bean.Classa;
import com.zcf.zhenxuan.mapper.ClassaMapper;
import com.zcf.zhenxuan.service.ClassaService;
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
public class ClassaServiceImpl extends ServiceImpl<ClassaMapper, Classa> implements ClassaService {

    @Autowired
    ClassaMapper classaMapper;

    @Override
    public Body addclassa(String classaname, String attributeid) {
        Classa classa = new Classa();
        classa.setClassaid(Hutool.getId());
        classa.setAddtime(DateUtils.formatTimeNow());
        classa.setClassaname(classaname);
        classa.setAttributeid(attributeid);
        Integer insert = classaMapper.insert(classa);
        if (insert > 0) {
            return Body.BODY_200;
        }

        return Body.BODY_500;
    }

    @Override
    public Body updateclassa(String classaid, String classaname) {

        Classa classa = classaMapper.selectById(classaid);
        classa.setClassaname(classaname);
        Integer integer = classaMapper.updateById(classa);
        if (integer > 0) {
            return Body.BODY_200;
        }
        return Body.BODY_500;
    }

    @Override
    public Body getclassalist(String attributeid) {

        EntityWrapper<Classa> entityWrapper = new EntityWrapper<Classa>();
        entityWrapper.eq("attributeid", attributeid);
        entityWrapper.orderBy("addtime", false);
        List<Classa> classas = classaMapper.selectList(entityWrapper);

        return Body.newInstance(classas);
    }

    @Override
    public Body deleteclassa(String classaid) {
        Integer integer = classaMapper.deleteById(classaid);
        if (integer > 0) {
            return Body.BODY_200;
        }
        return Body.BODY_500;
    }

    @Override
    public Body getclassainfo(String classaid) {
        Classa classa = classaMapper.selectById(classaid);
        return Body.newInstance(classa);
    }
}
