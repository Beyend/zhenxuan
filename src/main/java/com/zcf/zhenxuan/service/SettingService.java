package com.zcf.zhenxuan.service;

import com.baomidou.mybatisplus.service.IService;
import com.zcf.zhenxuan.bean.Settings;
import com.zcf.zhenxuan.bean.Signlog;
import com.zcf.zhenxuan.util.json.Body;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kmr123
 * @since 2019-01-16
 */
public interface SettingService extends IService<Settings> {

    Body addSetting(String liwu, String type, String num);

    Body changeSetting(String setid, String liwu, String type, String num);

    Body getSettingInfo(String setid);
}
