package com.zcf.zhenxuan.controller;


import com.zcf.zhenxuan.service.LiwulogService;
import com.zcf.zhenxuan.util.json.Body;
import com.zcf.zhenxuan.vo.in.UserParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author kmr123
 * @since 2019-01-16
 */
@Controller
@RequestMapping("/liwulog")
@CrossOrigin
@ResponseBody
public class LiwulogController {
    @Autowired
    LiwulogService liwulogService;

    /**
     * 查看获奖名单
     *
     * @return
     */
    @PostMapping("/getLiwuloglist")
    public Body getLiwuloglist(UserParam param) {
        return liwulogService.getLiwuloglist(param);
    }

    /**
     * 填写中奖姓名电话
     * @param liwulogid
     * @param lianxi
     * @param tel
     * @return
     */
    @PostMapping("/zhongjiang")
    public Body zhongjiang(String liwulogid, String lianxi, String tel) {
        return liwulogService.zhongjiang(liwulogid, lianxi, tel);
    }

}

