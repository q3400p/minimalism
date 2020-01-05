package com.windf.core.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 分页对象
 * 自动计算一些分页信息
 */
public class SearchData extends PageParameter implements Serializable {
	/**
	 * 要查询的列信息
	 * 如果为空，就查出所有的
	 * 不是查出所有的列，需要某些列，就不用所有的查出来了
	 */
	private Map<String, Object> fieldMap;
	/**
	 * 排序方式
     * 如果没有，不进行排序
	 */
	private List<OrderItem> order;
	/**
	 * 查询条件，根据条件进行查询
     * 如果为空，查询所有
	 */
	private Map<String, Object> condition;
	/**
	 * 分页信息
	 * 如果没有，进行分页
	 */
	private PageParameter pageParameter;

}
