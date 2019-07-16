package com.zcf.zhenxuan.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.zcf.zhenxuan.bean.Evaluate;
import com.zcf.zhenxuan.bean.Order;
import com.zcf.zhenxuan.mapper.EvaluateMapper;
import com.zcf.zhenxuan.mapper.OrderMapper;
import com.zcf.zhenxuan.service.EvaluateService;
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
 * @since 2018-12-18
 */
@Service
public class EvaluateServiceImpl extends ServiceImpl<EvaluateMapper, Evaluate> implements EvaluateService {

    @Autowired
    EvaluateMapper evaluateMapper;
    @Autowired
    OrderMapper orderMapper;

    @Override
    public Body addevaluate(String orderid, String userid, String score, String content, String img) {
        Order order = orderMapper.selectById(orderid);
        Evaluate evaluate = new Evaluate();
        evaluate.setEvaluateid(Hutool.getId());
        evaluate.setAddtime(DateUtils.formatTimeNow());
        evaluate.setOrderid(orderid);
        evaluate.setGoodsid(order.getGoodsid());
        evaluate.setShopid(order.getShopid());
        evaluate.setUserid(userid);
        if (!StringUtils.isEmpty(content)) {
            evaluate.setContent(content);
        }
        if (!StringUtils.isEmpty(score)) {
            evaluate.setScore(score);
        }
        if (!StringUtils.isEmpty(img)) {
            evaluate.setImg(img);
        }

        Integer insert = evaluateMapper.insert(evaluate);
        if (insert > 0) {
            order.setStatus("4");
            orderMapper.updateById(order);
            return Body.BODY_200;
        }
        return Body.BODY_500;
    }

    @Override
    public Body getevaluatlist(String goodsid) {
        EntityWrapper<Evaluate> evaluateEntityWrapper = new EntityWrapper<>();
        evaluateEntityWrapper.eq("goodsid", goodsid);
        evaluateEntityWrapper.orderBy("addtime", false);
        List<Evaluate> evaluates = this.selectList(evaluateEntityWrapper);
        return Body.newInstance(evaluates);
    }
}
