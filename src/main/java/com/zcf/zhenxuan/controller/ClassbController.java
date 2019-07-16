package com.zcf.zhenxuan.controller;


import com.zcf.zhenxuan.service.ClassbService;
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
@RequestMapping("/classb")
@ResponseBody
@CrossOrigin
public class ClassbController {
    @Autowired
    ClassbService classbService;

    /**
     * 添加二级分类
     *
     * @param classaid
     * @param classbname
     * @param classbimg
     * @return
     */
    @PostMapping("/addclassb")
    public Body addclassb(String classaid, String classbname, String classbimg) {
        return classbService.addclassb(classaid, classbname, classbimg);

    }

    /**
     * 修改二级分类
     *
     * @param classbid
     * @param classbname
     * @param classbimg
     * @return
     */
    @PostMapping("/updateclassb")
    public Body updateclassb(String classbid, String classbname, String classbimg) {
        return classbService.updateclassb(classbid, classbname, classbimg);
    }

    /**
     * 删除二级分类
     *
     * @param classbid
     * @return
     */
    @PostMapping("/deleteclassb")
    public Body deleteclassb(String classbid) {
        return classbService.deleteclassb(classbid);
    }

    /**
     * 根据一级分类查看二级分类
     *
     * @param classaid
     * @return
     */
    @PostMapping("/getclassblist")
    public Body getclassblist(String classaid) {
        return classbService.getclassblist(classaid);
    }

    /**
     * 查看二级分类详情
     *
     * @param classbid
     * @return
     */
    @PostMapping("/getclassbinfo")
    public Body getclassbinfo(String classbid) {
        return classbService.getclassbinfo(classbid);
    }

    /**
     * 查看代理分类成交数
     * @param classaid
     * @return
     */
    @PostMapping("/agency")
    public Body getAgencyClassList(String classaid){
        return classbService.getAgencyClassList(classaid);
    }

}

