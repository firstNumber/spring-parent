package com.weilai.common.cache;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.weilai.common.exception.BusiException;
import com.weilai.common.redis.JedisUtil;
import com.weilai.common.util.SysConst;

/**
 * ********************************* 缓存操作类 1.增加缓存: 缓存类型共两类, list和obj key共两类,
 * CacheEnum和自定义,增加自定义key的缓存需指定缓存时间 2.查询缓存: 对应增加缓存类型的两类, list和obj
 * key也是CacheEnum和自定义,当缓存中不存在对应key时,
 * CacheEnum会通过定义的service去数据库查询,并自动加热缓存,缓存时间为设置的时间.
 * 自定义key会直接返回null,不去service查询(可在cachefactory自定义处理)
 *
 * 
 * @author: liyongzhen
 * @createdAt: 2016年8月29日上午9:16:25
 **********************************
 */
public class CacheMan {

	private static Log log = LogFactory.getLog(CacheMan.class);

	public static String getString(String cacheGroup, String key) {
		return get(cacheGroup, key, String.class);
	}

	public static String getString(String cacheGroup, Integer key) {
		return get(cacheGroup, key + "", String.class);
	}

	public static String getString(CacheEnum cacheGroup, String key) {
		return get(cacheGroup, key, String.class);
	}

	public static String getString(CacheEnum cacheGroup, Integer key) {
		return get(cacheGroup, key + "", String.class);
	}

	public static Integer getInteger(CacheEnum cacheGroup, String key) {
		return get(cacheGroup, key, Integer.class);
	}

	public static Integer getInteger(CacheEnum cacheGroup, Integer key) {
		return get(cacheGroup, key + "", Integer.class);
	}

	public static Integer getInteger(String cacheGroup, String key) {
		return get(cacheGroup, key, Integer.class);
	}

	public static Integer getInteger(String cacheGroup, Integer key) {
		return get(cacheGroup, key + "", Integer.class);
	}

	public static Long getLong(CacheEnum cacheGroup, String key) {
		return get(cacheGroup, key, Long.class);
	}

	public static Long getLong(CacheEnum cacheGroup, Integer key) {
		return get(cacheGroup, key + "", Long.class);
	}

	public static Long getLong(String cacheGroup, String key) {
		return get(cacheGroup, key, Long.class);
	}

	public static Long getLong(String cacheGroup, Integer key) {
		return get(cacheGroup, key + "", Long.class);
	}

	public static <T> T get(CacheEnum cacheGroup, String key, Class<T> clazz) {
		return get(cacheGroup.toString(), key, clazz);
	}

	public static <T> T get(CacheEnum cacheGroup, Integer key, Class<T> clazz) {
		return get(cacheGroup.toString(), key + "", clazz);
	}

	public static <T> T get(String cacheGroup, Integer key, Class<T> clazz) {
		return get(cacheGroup, key + "", clazz);
	}

	@SuppressWarnings("unchecked")
	public static <T> T get(String cacheGroup, String key, Class<T> clazz) {
		try {
			String fullKey = CacheUtil.getCacheName(cacheGroup, key);
			T t = JedisUtil.getObject(fullKey, clazz);
			if (t == null) {
				Object obj = CacheFactory.factory(cacheGroup, key);
				if (obj != null) {
					CacheEnum cnEnum = CacheEnum.contains(cacheGroup);
					if (cnEnum != null) {
						JedisUtil.setKOT(fullKey, obj, cnEnum.getCacheSecond());
					} // 非枚举项定义的缓存,不保存
					t = (T) obj;
				}
			}
			return t;
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			return null;
		}
	}

	/**
	 * 根据缓存全名获取值
	 * 
	 * @param fullKey
	 * @param clazz
	 * @return
	 * @Author: liupeng
	 * @Date: 2017年7月14日
	 */
	public static <T> T get(String fullKey, Class<T> clazz) {
		try {
			T t = JedisUtil.getObject(fullKey, clazz);
			return t;
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			return null;
		}
	}

	/**
	 * 自增
	 * 
	 * @param cacheGroup
	 * @param key
	 * @param num
	 * @return
	 * @Author: liupeng
	 * @Date: 2017年6月9日
	 */
	public static long incrBy(String cacheGroup, String key, long num) {
		try {
			String fullKey = CacheUtil.getCacheName(cacheGroup, key);
			return JedisUtil.incrBy(fullKey, num);
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			return -1;
		}
	}

	/**
	 * 自减
	 * 
	 * @param cacheGroup
	 * @param key
	 * @param num
	 * @return
	 * @Author: liupeng
	 * @Date: 2017年6月9日
	 */
	public static long decrBy(String cacheGroup, String key, long num) {
		try {
			String fullKey = CacheUtil.getCacheName(cacheGroup, key);
			return JedisUtil.decrBy(fullKey, num);
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			return -1;
		}
	}

	/**
	 * 根据key和class获取缓存的list cacheName+value=缓存的key
	 * 
	 * @param cahceGroup
	 *            缓存key前缀
	 * @param key
	 *            缓存的key值
	 * @param clazz
	 *            list中的class对象
	 * @return
	 * @Author: liyongzhen
	 * @Date: 2016年4月1日
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> getList(String cahceGroup, String key, Class<T> clazz) {
		try {
			String fullKey = CacheUtil.getCacheName(cahceGroup, key);
			List<T> list = JedisUtil.getKList(fullKey, clazz);
			if (list == null) {
				Object obj = CacheFactory.factory(cahceGroup, key);
				if (obj != null) {
					list = (List<T>) obj;
					CacheEnum cnEnum = CacheEnum.contains(cahceGroup);
					if (cnEnum != null) {
						JedisUtil.setKListT(fullKey, list, cnEnum.getCacheSecond());
					} // 非枚举项定义的缓存,不保存
				}
			}
			return list;
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			return null;
		}
	}

	/**
	 * 根据 cacheEnum 获取缓存
	 * 
	 * @param cahceGroup
	 * @param key
	 * @param clazz
	 * @return
	 * @Author: liyongzhen
	 * @Date: 2016年8月29日
	 */
	public static <T> List<T> getList(CacheEnum cahceGroup, String key, Class<T> clazz) {
		return getList(cahceGroup.toString(), key, clazz);
	}

	/**
	 * 缓存list,非枚举项缓存,需定义缓存时长
	 * 
	 * @param cacheGroup
	 * @param key
	 * @param valueList
	 * @param seconds
	 *            0 表示永久
	 * @Author: liyongzhen
	 * @Date: 2016年8月25日
	 */
	public static <T> void addList(String cacheGroup, String key, List<T> valueList, int seconds) {
		try {
			String fullKey = CacheUtil.getCacheName(cacheGroup, key);
			CacheEnum cnEnum = CacheEnum.contains(cacheGroup);
			if (cnEnum != null) {
				JedisUtil.setKListT(fullKey, valueList, cnEnum.getCacheSecond());
			} else {
				JedisUtil.setKList(fullKey, valueList);
			}
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		}
	}

	/**
	 * 缓存list,默认时间为enum中时间
	 * 
	 * @param cacheGroup
	 * @param key
	 * @param valueList
	 * @Author: liyongzhen
	 * @Date: 2016年8月25日
	 */
	public static <T> void addList(CacheEnum cacheGroup, String key, List<T> valueList) {
		addList(cacheGroup.toString(), key, valueList, cacheGroup.getCacheSecond());
	}

	/**
	 * 缓存对象,默认时间为enum中时间
	 * 
	 * @param cacheGroup
	 * @param key
	 * @param value
	 * @return
	 * @Author: liyongzhen
	 * @Date: 2016年8月25日
	 */
	public static Object add(CacheEnum cacheGroup, String key, Object value) {
		return add(cacheGroup.toString(), key, value, cacheGroup.getCacheSecond());
	}

	public static Object add(CacheEnum cacheGroup, Integer key, Object value) {
		return add(cacheGroup.toString(), key + "", value, cacheGroup.getCacheSecond());
	}

	public static Object add(String cacheGroup, Integer key, Object value, int seconds) {
		return add(cacheGroup, key + "", value, seconds);
	}

	/**
	 * 缓存对象,非枚举项缓存,需定义缓存时长
	 * 
	 * @param cacheGroup
	 * @param key
	 * @param value
	 * @param seconds
	 *            0 表示永久
	 * @return
	 * @Author: liyongzhen
	 * @Date: 2016年8月25日
	 */
	public static Object add(String cacheGroup, String key, Object value, int seconds) {
		try {
			String fullKey = CacheUtil.getCacheName(cacheGroup, key);
			JedisUtil.setKOT(fullKey, value, seconds);
			return value;
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			return null;
		}
	}

	/**
	 * 删除缓存 cacheName+value=缓存的key
	 * 
	 * @param cacheEnum
	 *            缓存key前缀
	 * @param key
	 *            缓存的key值
	 * @Author: liyongzhen
	 * @Date: 2016年4月1日
	 */
	public static void remove(String cacheGroup, String key) {
		try {
			JedisUtil.remove(CacheUtil.getCacheName(cacheGroup, key));
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		}
	}

	/**
	 * 根据key前缀删除缓存 cacheName+value=缓存的key
	 * 
	 * @param cacheName
	 *            缓存key前缀
	 * @Author: liyongzhen
	 * @Date: 2016年4月1日
	 */
	public static void removeByKeyPattern(String cacheName) {
		try {
			Set<String> keys = JedisUtil.keys(CacheUtil.getCacheName(cacheName) + "*");
			if (CollectionUtils.isEmpty(keys) || keys.size() < 1) {
				return;
			}
			String[] delKeys = new String[keys.size()];
			delKeys = keys.toArray(delKeys);
			log.error("本次清空的key:" + keys.toString());
			JedisUtil.remove(delKeys);
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		}

	}

	/**
	 * 根据前缀模糊匹配,返回所有key
	 * 
	 * @param keyPrefix
	 * @return
	 * @Author: liyongzhen
	 * @Date: 2017年5月26日
	 */
	public static Set<String> getKeysByPattern(String keyPrefix) {
		Set<String> keys = JedisUtil.keys(CacheUtil.getCacheName(keyPrefix) + "*");
		return keys;
	}

	/**
	 * 删除所有缓存
	 * 
	 * @param isAll
	 *            true表示本项目所有,false表示一些不能删除的除外(例如web的token)
	 * @Author: liyongzhen
	 * @Date: 2016年6月30日
	 */
	public static void removeAll(boolean isAll) {
		try {
			Set<String> keys = JedisUtil.keys(CacheUtil.SYSTEM_NAME + "*");
			if (!isAll) {
				Iterator<String> it = keys.iterator();
				while (it.hasNext()) {
					String name = it.next();
					if (isNotDelName(name)) {
						it.remove();
					}
				}
			}
			if (CollectionUtils.isEmpty(keys) || keys.size() < 1) {
				log.error("本次清空的key:没有符合条件的!");
				return;
			}
			String[] delKeys = new String[keys.size()];
			delKeys = keys.toArray(delKeys);
			log.error("本次清空的key:" + keys.toString());
			JedisUtil.remove(delKeys);
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		}
	}

	/**
	 * 判断缓存是否能删除
	 * 
	 * @param name
	 * @return
	 * @Author: liyongzhen
	 * @Date: 2016年6月30日
	 */
	private static boolean isNotDelName(String name) {
		if (name.indexOf(CacheUtil.SYSTEM_NAME) == -1 // 不是这个项目的
		) {
			return true;
		}
		return false;
	}

	/**
	 * 查询缓存,如果不存在,直接返回null,不再去DB查询
	 * 
	 * @param cacheEnum
	 * @param key
	 * @return
	 * @Author: liyongzhen
	 * @Date: 2016年11月14日
	 */
	public static <T> T getNoFactory(CacheEnum cacheEnum, String key, Class<T> clazz) {
		try {
			String fullKey = CacheUtil.getCacheName(cacheEnum.toString(), key);
			T t = JedisUtil.getObject(fullKey, clazz);
			return t;
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			return null;
		}
	}

	/**
	 * 用于查看换成内容
	 * 
	 * @param fullKey
	 *            完整的key
	 * @return
	 * @Author: liyongzhen
	 * @Date: 2017年2月17日
	 */
	public static String view(String fullKey) {
		String result = JedisUtil.view(fullKey);
		return result;

	}

	/**
	 * 锁定key,专用于防止重复提交,如果获取锁失败,抛出异常
	 * 
	 * @param key
	 * @Author: liyongzhen
	 * @Date: 2017年3月22日
	 */
	public static void postLock(String key) {
		postLock(key, SysConst.OPERATION_TIME);
	}

	/**
	 * 锁定key,专用于防止重复提交,如果获取锁失败,抛出异常
	 * 
	 * @param key
	 * @param seconds
	 *            锁定时间
	 * @Author: liyongzhen
	 * @Date: 2017年5月26日
	 */
	public static void postLock(String key, int seconds) {
		String fullKey = CacheUtil.getCacheName(key, "");
		boolean success = JedisUtil.setnx(fullKey, "", (long) seconds * 1000);
		if (!success) {
			log.error("正在处理中,重复提交! key:" + key + ",预计剩余时间:" + JedisUtil.ttl(key) + "秒");
			throw new BusiException("正在处理中,请不要重复提交!");
		}
	}

	/**
	 * 解锁key
	 * 
	 * @param key
	 * @Author: liyongzhen
	 * @Date: 2017年3月22日
	 */
	public static void unLock(String key) {
		remove(key, "");
	}

}
