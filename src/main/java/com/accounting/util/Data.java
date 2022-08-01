package com.accounting.util;

import java.util.Date;

@lombok.Data
public class Data {
    Date createTime;
    Integer count;
	@Override
	public String toString() {
		return "Data [createTime=" + createTime + ", count=" + count + "]";
	}

    
}