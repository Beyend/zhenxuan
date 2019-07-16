package com.zcf.zhenxuan.controller;


import com.zcf.zhenxuan.service.EvaluateService;
import com.zcf.zhenxuan.util.json.Body;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author kmr123
 * @since 2018-12-18
 */
@Controller
@RequestMapping("/evaluate")
@ResponseBody
@CrossOrigin
public class EvaluateController {
    @Autowired
    EvaluateService evaluateService;

    /**
     * 发表评论
     *
     * @param orderid
     * @param userid
     * @param score
     * @param content
     * @param img
     * @return
     */
    @PostMapping("/addevaluate")
    public Body addevaluate(String orderid, String userid, String score, String content, String img) {
        return evaluateService.addevaluate(orderid, userid, score, content, img);
    }

    /**
     * 查看商品评论
     *
     * @param goodsid
     * @return
     */
    @PostMapping("/getevaluatlist")
    public Body getevaluatlist(String goodsid) {
        return evaluateService.getevaluatlist(goodsid);
    }

}

