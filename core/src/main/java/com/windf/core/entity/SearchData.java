package com.windf.core.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分页对象
 * 自动计算一些分页信息
 */
public class SearchData extends PageParameter implements Serializable {

	private static final SearchData emptySearchData = new SearchData();

	/**
	 * 获取空的搜索对象
	 * @return
	 */
	public static SearchData getEmptySearchData() {
		return emptySearchData;
	}

	/**
	 * 要查询的列信息
	 * 如果为空，就查出所有的
	 * 不是查出所有的列，需要某些列，就不用所有的查出来了
	 */
	private Map<String, Object> fieldMap = new HashMap<>();
	/**
	 * 排序方式
     * 如果没有，不进行排序
	 */
	private List<OrderItem> order = new ArrayList<>();
	/**
	 * 查询条件，根据条件进行查询
     * 如果为空，查询所有
	 */
	private Map<String, Object> condition = new HashMap<>();
	/**
	 * 分页信息
	 * 如果没有，进行分页
	 */
	private PageParameter pageParameter = new PageParameter();

	public Map<String, Object> getFieldMap() {
		return fieldMap;
	}

	public void setFieldMap(Map<String, Object> fieldMap) {
		this.fieldMap = fieldMap;
	}

	public List<OrderItem> getOrder() {
		return order;
	}

	public void setOrder(List<OrderItem> order) {
		this.order = order;
	}

	public Map<String, Object> getCondition() {
		return condition;
	}

	public void setCondition(Map<String, Object> condition) {
		this.condition = condition;
	}

	public PageParameter getPageParameter() {
		return pageParameter;
	}

	public void setPageParameter(PageParameter pageParameter) {
		this.pageParameter = pageParameter;
	}
}
