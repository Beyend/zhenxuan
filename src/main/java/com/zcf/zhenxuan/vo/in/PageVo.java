package com.zcf.zhenxuan.vo.in;

/**
 * 分页参数公共 vo Created by shenguohao
 */

public class PageVo {
	private Integer pageNum = 1;
	private Integer pageSize = 10;

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

}