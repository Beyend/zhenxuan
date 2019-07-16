package com.zcf.zhenxuan.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.zcf.zhenxuan.bean.Classb;
import com.zcf.zhenxuan.bean.Goods;
import com.zcf.zhenxuan.mapper.ClassbMapper;
import com.zcf.zhenxuan.mapper.GoodsMapper;
import com.zcf.zhenxuan.service.ClassbService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zcf.zhenxuan.util.DateUtils;
import com.zcf.zhenxuan.util.Hutool;
import com.zcf.zhenxuan.util.json.Body;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
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
public class ClassbServiceImpl extends ServiceImpl<ClassbMapper, Classb> implements ClassbService {
    @Autowired
    ClassbMapper classbMapper;
    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public Body addclassb(String classaid, String classbname, String classbimg) {
        Classb classb = new Classb();
        classb.setClassbid(Hutool.getId());
        classb.setAddtime(DateUtils.formatTimeNow());
        classb.setClassbimg(classbimg);
        classb.setClassbname(classbname);
        classb.setClassaid(classaid);
        Integer insert = classbMapper.insert(classb);
        if (insert > 0) {
            return Body.BODY_200;
        }
        return Body.BODY_500;
    }

    @Override
    public Body updateclassb(String classbid, String classbname, String classbimg) {
        Classb classb = classbMapper.selectById(classbid);
        classb.setClassbname(classbname);
        classb.setClassbimg(classbimg);
        Integer integer = classbMapper.updateById(classb);
        if (integer > 0) {
            return Body.BODY_200;
        }
        return Body.BODY_500;
    }

    @Override
    public Body deleteclassb(String classbid) {
        Integer integer = classbMapper.deleteById(classbid);
        if (integer > 0) {
            return Body.BODY_200;
        }
        return Body.BODY_500;
    }

    @Override
    public Body getclassblist(String classaid) {
//        EntityWrapper<Classb> classbEntityWrapper = new EntityWrapper<>();
//        classbEntityWrapper.eq("classaid", classaid);
//        classbEntityWrapper.orderBy("addtime", false);
//        List<Classb> classbs = classbMapper.selectList(classbEntityWrapper);

        String begDate = DateUtils.formatDate(new Date(),"yyyy-MM-dd 00:00:00");
        String endDate = DateUtils.formatDate(new Date(),"yyyy-MM-dd 23:59:59");
        List<Classb> classbs = classbMapper.getClassB(begDate,endDate,classaid);

        String begMonth = DateUtils.formatDate(DateUtils.getTimesMonthmorning(),"yyyy-MM-dd HH:mm:ss");
        String endMonth = DateUtils.formatDate(DateUtils.getTimesMonthnight(),"yyyy-MM-dd HH:mm:ss");
        List<Classb> classbList = classbMapper.getClassB(begMonth,endMonth,classaid);

        if(!CollectionUtils.isEmpty(classbs)){
            for(Classb classb : classbs){
                for(Classb classb1 : classbList ){
                    if(classb.getClassbid().equals(classb1.getClassbid())){
                        classb.setMonthnum(classb1.getTotolnum());
                        classb.setMonthmoney(classb1.getTotolmomey());
                        break;
                    }
                }
            }

        }

        return Body.newInstance(classbs);
    }

    @Override
    public Body getclassbinfo(String classbid) {
        Classb classb = classbMapper.selectById(classbid);


        return Body.newInstance(classb);
    }

    @Override
    public Body getAgencyClassList(String classaid){
        List<Classb> classbList = classbMapper.getAgencyClassList(classaid);
        return Body.newInstance(classbList);
    }

}
