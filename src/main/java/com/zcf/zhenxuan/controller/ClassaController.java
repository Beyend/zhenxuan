package com.zcf.zhenxuan.controller;


import com.zcf.zhenxuan.service.ClassaService;
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
@RequestMapping("/classa")
@ResponseBody
@CrossOrigin
public class ClassaController {

    @Autowired
    ClassaService classaService;

    /**
     * 添加一级分类
     *
     * @param classaname
     * @param attributeid
     * @return
     */
    @PostMapping("/addclassa")
    public Body addclassa(String classaname,String attributeid) {
        return classaService.addclassa(classaname,attributeid);
    }

    /**
     * 修改一级分类
     *
     * @param classaid
     * @param classaname
     * @return
     */
    @PostMapping("/updateclassa")
    public Body updateclassa(String classaid, String classaname) {
        return classaService.updateclassa(classaid, classaname);
    }

    /**
     * 查看大属性一级分类
     *
     * @param attributeid
     * @return
     */
    @PostMapping("/getclassalist")
    public Body getclassalist(String attributeid) {
        return classaService.getclassalist(attributeid);
    }

    /**
     * 删除一级分类
     *
     * @param classaid
     * @return
     */
    @PostMapping("/deleteclassa")
    public Body deleteclassa(String classaid) {
        return classaService.deleteclassa(classaid);
    }

    /**
     * 查看一级分类详情
     *
     * @param classaid
     * @return
     */
    @PostMapping("/getclassainfo")
    public Body getclassainfo(String classaid) {
        return classaService.getclassainfo(classaid);
    }


}

