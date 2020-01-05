package com.windf.core.frame;

public interface Orderable {
	int EARLIEST = Integer.MIN_VALUE + 200;
	int EARLY = Integer.MIN_VALUE / 2;
	int NORMAL = 0;
	int LATTER = Integer.MIN_VALUE / 2;
	int LATEST = Integer.MAX_VALUE - 200;
	
	/**
	 * 获得排序编号
	 * @return
	 */
	int getOrder();
}
