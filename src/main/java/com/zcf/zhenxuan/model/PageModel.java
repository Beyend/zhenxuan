package com.zcf.zhenxuan.model;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;

import com.zcf.zhenxuan.bean.User;
import com.zcf.zhenxuan.util.UploadImgUtils;
import com.zcf.zhenxuan.util.json.Body;
import com.zcf.zhenxuan.vo.in.UserParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

public class PageModel {


    /**
     * 分页
     *
     * @param param
     * @return
     */
//    public Body getUsers(UserParam param) {
//        Page<User> page = new Page<User>(param.getPageNum(), param.getPageSize());
//        EntityWrapper<User> eWrapper = new EntityWrapper<User>(param.getUser());
//          List<User> list = userMapper.selectList(eWrapper);
//        List<User> userlist = userMapper.selectPage(page, eWrapper);
//        Map<Object, Object> map = new HashMap<>();
//        map.put("total", 50);
//        map.put("data", null);
//        return Body.newInstance(map);
//    }

    /**
     * 上传文件
     * @param file
     * @return
     * @throws Exception
     */
    public Body shangchuan(MultipartFile[] file) throws Exception {
        String imgPath = UploadImgUtils.uploadFiles(file);
//        Ad ad = new Ad();
//        ad.setAdid(Hutool.getId());
//        ad.setAdimg(imgPath);
        //adMapper.insert(ad);
        return Body.newInstance(imgPath);
    }
}
