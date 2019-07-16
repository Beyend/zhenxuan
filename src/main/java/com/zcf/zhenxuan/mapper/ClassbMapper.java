package com.zcf.zhenxuan.mapper;

import com.zcf.zhenxuan.bean.Classb;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author kmr123
 * @since 2018-12-18
 */
public interface ClassbMapper extends BaseMapper<Classb> {
    List<Classb> getClassB(@Param(value = "begDate") String begDate,@Param(value = "endDate") String endDate,@Param(value = "classaid") String classaid);

    List<Classb> getAgencyClassList(@Param(value = "classaid") String classaid);
}
