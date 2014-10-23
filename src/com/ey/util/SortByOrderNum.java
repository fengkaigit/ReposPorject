/**  
 * Desc:  根据排序码进行排序 
 * @title SortByOrderNum.java   
 * @author zhanglibin@ieds.com.cn  
 * @update 2012-3-24 上午11:05:18  
 * @version V1.0 
 * Note: This content is restricted to IEDS within the company 
			circulated to prohibit leakage as well as for other commercial purposes 
 */
package com.ey.util;

import java.util.Comparator;



/**
 * Desc:根据排序码进行排序
 * 
 * @version 1.0
 * @author zhanglibin@ieds.com.cn
 * @update 2012-3-24 上午11:05:18
 */

public class SortByOrderNum implements Comparator<Object> {
	private static SortByOrderNum sort;

	public static SortByOrderNum getSort() {
		if (sort == null)
			sort = new SortByOrderNum();
		return sort;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(Object o1, Object o2) {
		int num = 0;
		
		return num;
	}

}
