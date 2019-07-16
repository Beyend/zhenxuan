package com.zcf.zhenxuan.controller;


import com.zcf.zhenxuan.service.SettingService;
import com.zcf.zhenxuan.util.json.Body;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author kmr123
 * @since 2019-01-16
 */
@Controller
@RequestMapping("/setting")
@CrossOrigin
@ResponseBody
public class SettingController {
    @Autowired
    SettingService settingService;


    /**
     * 修改礼物设置
     * @param setid
     * @param liwu
     * @param type
     * @param num
     * @return
     */
    @PostMapping("/changeSetting")
    public Body changeSetting(String setid, String liwu, String type, String num) {
        return settingService.changeSetting(setid, liwu, type, num);
    }

    /**
     * 查看礼物设置详情
     * @param setid
     * @return
     */
    @PostMapping("/getSettingInfo")
     public  Body  getSettingInfo( String setid){
        return  settingService.getSettingInfo(setid);
     }
}

