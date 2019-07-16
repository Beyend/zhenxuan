package com.zcf.zhenxuan.service;

import com.zcf.zhenxuan.bean.User;
import com.baomidou.mybatisplus.service.IService;
import com.zcf.zhenxuan.util.json.Body;
import com.zcf.zhenxuan.vo.in.UserParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kmr123
 * @since 2018-12-18
 */
public interface UserService extends IService<User> {

    Body login(String phone, String pwd);

    Body regist(String phone, String pwd);

    Body updatePwd(String userid, String oldPwd, String newPwd);

    Body forgetPwd(String phone, String newPwd);

    Body sendCode(String userid, String phone);

    Body updateUser(String userid, String username, String userimg);

    Body changeaddress(String userid, String city);

    Body sign(String userid);

    Body changephone(String userid, String oldphone, String newphone);

    Body getuserlistbycity(UserParam param);

    Body getuserinfo(String userid);

    Body shangchuan(MultipartFile[] file);

    Body getuserlistbyshop(UserParam param);

    Body deleteUser(String userid);
}
