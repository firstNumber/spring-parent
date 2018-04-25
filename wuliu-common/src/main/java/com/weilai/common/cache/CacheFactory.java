package com.weilai.common.cache;

import org.apache.commons.lang.StringUtils;
import com.weilai.common.util.SpringContextUtil;

/**
 * *********************************
* 缓存工厂类 ,用于 key在缓存查不到时进行后续操作
*
* @author: liyongzhen
* @createdAt: 2016年8月29日上午9:22:29
**********************************
 */
public class CacheFactory {
	
	/**
	 * 如果cacheName是CacheEnum枚举项,则默认去对应service的get4CacheFactory查询.
	 * 如果不是CacheEnum枚举项 默认返回null
	* @param cacheName
	* @param key
	* @return
	* @throws Exception
	* @Author: liyongzhen
	* @Date: 2016年8月29日
	 */
	public static Object factory(String cacheName, Object key) throws Exception {
		Object obj = null;
		CacheEnum cnEnum = CacheEnum.contains(cacheName);
		if(cnEnum!=null){//如果是枚举项
			if(StringUtils.isBlank(cnEnum.getServiceName())){
				return obj;
			}
			Object service = SpringContextUtil.getBean(cnEnum.getServiceName());
			java.lang.reflect.Method m = service.getClass().getMethod("get4CacheFactory",CacheEnum.class,Object.class);
			obj = m.invoke(service,cnEnum,key);
		}
		return obj;
	}
}
