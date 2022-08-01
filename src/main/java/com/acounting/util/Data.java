package com.acounting.util;

import java.util.Date;

@lombok.Data
public class Data {
	private Date createTime;
	private Integer count;
	@Override
	public String toString() {
		return "Data [createTime=" + createTime + ", count=" + count + "]";
	}

    
}