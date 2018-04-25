package com.weilai.common.util;

/**
 * 一些系统公用常量
 */
public class CommonConst {
	
	
	/**
	 * 排序方式
	 * @author liyongzhen
	 *
	 */
	public enum SortBy{
		ASC,DESC;
	}
	
	/**
	 * 需要更新缓存的 操作类型
	 */
	public enum UpdateCacheType{
		CREATE,UPDATE,UPDATE4SELECTIVE,DELETE,DELETE_ID
	}
	
}
