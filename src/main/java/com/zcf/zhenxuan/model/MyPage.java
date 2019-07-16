package com.zcf.zhenxuan.model;

import com.baomidou.mybatisplus.plugins.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class MyPage<T> extends Page<T> {
    private static final long serialVersionUID = 5194933845448697148L;

    private Integer selectInt;
    private String selectStr;

    public MyPage(int current, int size) {
        super(current, size);
    }


}